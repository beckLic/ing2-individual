Decisiones de Mapeo y Diseño Destacadas:

Herencia de Producto (@Inheritance(strategy = InheritanceType.JOINED)):
Decidí usar JOINED en lugar de SINGLE_TABLE. Aunque SINGLE_TABLE es más rápido, JOINED creará una tabla para Producto, otra para Software y otra para Hardware unidas por el ID. Esto es mucho más limpio a nivel de base de datos porque evita tener decenas de columnas con valores NULL (por ejemplo, numero_serie sería nulo para un Software).

El CUIT del Proveedor:
El diagrama original proponía int cuit. Sin embargo, en Argentina un CUIT tiene 11 dígitos, lo cual supera el valor máximo permitido por un tipo de dato int en Java y MySQL (2.147.483.647). Lo ajusté a Long para evitar errores de truncamiento de datos en la base.

Colección de Especificaciones en Hardware:
Para la colección de especificaciones (que son simples String), en lugar de crear una entidad completa adicional, utilicé @ElementCollection. Esto le dice a Hibernate que cree una tabla dependiente hardware_especificaciones pero sin complejidad extra de entidades.

Relaciones Bidireccionales vs Unidireccionales:
En relaciones fuertes de composición como OrdenDeCompra -> Detalle, usé @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true). Esto significa que si eliminás una orden, sus detalles se borran automáticamente, respetando el concepto de composición.

Tipos de Datos Temporales y Enum:
Usé @Enumerated(EnumType.STRING) para el enum TipoMovimiento de forma que en la base de datos se guarde el texto "ENTRADA" o "SALIDA" (mucho más legible que un 0 o 1). También usé @Temporal en las fechas para definir claramente si necesitamos solo la fecha o también la hora (TIMESTAMP).

¿Por qué usamos DTOs en este proyecto? 

Protección contra LazyInitializationException: Las relaciones @ManyToMany y @OneToMany son perezosas (Lazy) por defecto. Si pasáramos la Entidad OrdenDeCompra a la vista (Thymeleaf) e intentáramos iterar sus Detalles, y la sesión de Hibernate ya se cerró en el Service, la app tiraría error. Al usar Mappers dentro del Service, obligamos a Hibernate a buscar los Detalles mientras la sesión sigue viva, cargándolos en el DTO de forma segura.

Ruptura de Dependencias Circulares (StackOverflow): Como viste en ProveedorDTO y PromocionDTO, no tienen una lista de ProductoDTO, sino una simple lista de Strings (nombresProductos o ids). Si tuviéramos un ProveedorDTO que contiene ProductoDTOs, y a su vez cada ProductoDTO tiene ProveedorDTOs, al intentar convertir esto a JSON (por ejemplo, para una API) o al iterarlo, entraríamos en un loop infinito.

Aplanamiento de datos para la Vista: En OrdenDeCompraDTO, no puse el objeto ProveedorDTO entero. En su lugar, agregué proveedorCuit y proveedorRazonSocial. Esto se llama "aplanar" o "flattening" de datos. Hace que Thymeleaf sea mucho más fácil de usar, ya que la vista no tiene que navegar objetos profundos (ej. orden.proveedor.razonSocial).

Seguridad básica (UsuarioDTO): Como menciono en los comentarios del código, en un UsuarioDTO que se devuelve a la capa de presentación, nunca incluimos el password. Si lo necesitamos para el momento específico de validar el login, deberíamos crear un UsuarioLoginDTO separado.

Mappers Manuales (@Component): En vez de usar herramientas mágicas como MapStruct, decidí crear Mappers explícitos (como ProductoMapper). Esto es fundamental para entender de verdad cómo Java hace casting con instanceof para saber si un Producto es Software o Hardware, y mapearlo al DTO correcto.



1. Spring Data JPA (Capa de Acceso a Datos)
   Ahorro de código: Al extender JpaRepository<Entidad, TipoId>, heredamos métodos como .save(), .findById(), o .findAll() sin tener que escribir una sola línea de SQL.

Query Methods: En ProductoRepository o OrdenDeCompraRepository, creé el método findByEliminadoFalse(). Spring Boot, de forma "mágica", lee el nombre del método y genera la query SQL equivalente a SELECT * FROM tabla WHERE eliminado = false.

2. Contratos Restrictivos (Capa de Servicio)
   Las interfaces que creé (ej. ProductoService) declaran que solo reciben y devuelven DTOs.

El controlador (que haremos en la próxima etapa) jamás va a tocar la base de datos de forma directa. Su única forma de acceder a los datos es inyectar un Service y darle un DTO.

3. Lógica de Negocio (Implementaciones)
   Baja Lógica (Soft Delete): En los métodos eliminarProducto() y eliminarOrden() evité el uso de repository.delete(). En su lugar, buscamos la entidad, hacemos setEliminado(true) y la volvemos a guardar (repository.save()). Esto simula un borrado sin perder la trazabilidad de los datos en la base.

Transaccionalidad: Usé la anotación @Transactional en los métodos que modifican o crean datos. Si en el método agregarDetalle() ocurre un error justo después de agregar el detalle pero antes de actualizar el total, Spring hace un rollback de la transacción completa, garantizando la consistencia de la base de datos.

Conversión Manual: En los métodos crearX, transformamos el DTO de entrada en una Entidad usando constructores o el patrón Builder. Luego, la guardamos, y finalmente usamos nuestros Mappers de la Etapa 2 para devolver la respuesta.

CONTROLADORES

UsuarioController (Login Text-Plain): Tal como solicitaste en el objetivo inicial (cero Spring Security), el login es puramente lógico. Se recibe un UsuarioLoginDTO y se pasa al UsuarioService. Si el texto plano coincide, redirige a /home. Si falla, lanza una excepción en el servicio que el controlador atrapa y redirige nuevamente al login con la query string ?error=true, lo que permite que Thymeleaf muestre el mensaje de error.

Separación Producto / Software / Hardware: Mantuvimos ProductoController para listar y eliminar de forma genérica. Sin embargo, para crear o editar, usamos SoftwareController y HardwareController. Esto es porque un formulario de creación de Software requiere campos distintos (claveLicencia, enlaceDescarga) a los de un Hardware (numeroSerie, garantiaMeses).

Anotaciones Core de Spring MVC:

@Controller: Le dice a Spring que esta clase no devuelve datos puros (como un @RestController con JSON), sino que retorna el nombre de un archivo HTML (ej. "producto/lista").

@ModelAttribute("nombre"): Es el "pegamento" bidireccional entre la vista y Java. Si el usuario llena un formulario HTML, Spring arma automáticamente el DTO con esos datos.

@PathVariable y @RequestParam: El primero extrae variables que son parte de la ruta (ej. /eliminar/123), mientras que el segundo extrae variables que vienen como parámetros de búsqueda (?id=123).

Model: Es la "valija" donde metemos todos los DTOs y listas en el backend (con .addAttribute()) para que Thymeleaf pueda desempaquetarlos y dibujarlos en el frontend.