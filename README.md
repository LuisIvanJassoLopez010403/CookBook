# CookBook App


## **Objetivo del Proyecto**

**CookBook** es una innovadora aplicación diseñada para facilitar la búsqueda, organización y creación de recetas. Su objetivo principal es conectar a las personas mediante una red social culinaria que permita compartir recetas, descubrir nuevas ideas gastronómicas y personalizar la experiencia culinaria de cada usuario, esto gracias a la visión de que cada usuario pueda descubrir nuevas recetas con los ingredientes exactos que cuenta en su hogar y compartir sus mejores creaciones, convirtiéndola en una aplicación de recetas sumamente flexible.

---

## **¿Quién lo utilizaría?**

La plataforma fue desarrollada para todo tipo de personas ya que alguna vez todos han experimentado en la cocina, es por eso que CookBook puede ser para cualquier persona que quiera salir de su zona de confort y este preparada para descubrir y compartir sus mejores recetas. CookBook esta dirigida para:

1. Personas que buscan recetas rápidas y sencillas con ingredientes disponibles en casa, como estudiantes foráneos que carecen de los recursos necesarios para recetas exuberantes en ingredientes o empleados de oficina que carecen del tiempo para elaborar platillos complejos.

2. Amantes de la cocina, desde principiantes que disfrutan explorando y preparando nuevas recetas hasta chefs profesionales que desean compartir sus recetas o encontrar inspiración para nuevos platos.

3. Comunidades interesadas en recetas específicas como vegetarianas, veganas, postres, comida internacional, etc.

---

## **¿Cómo monetizar la aplicación?**

Varias posibles estrategias que CookBook puede implementar para generar ingresos incluyen:

1.  **Publicidad:** Implementar anuncios relevantes en la aplicación, como ofertas de supermercados, promociones de utensilios de cocina y marcas de empresas en la industria de la comida.

2.  **Suscripción Premium:** Ofrecer beneficios exclusivos como:

- Filtros avanzados para recetas (por dieta, nivel de dificultad, etc.).

- Acceso anticipado a nuevas funciones o contenido.

- Cashback en caso de compras internas.

- Clases premium de chefs mundialmente reconocidos.

- Eliminar anuncios.

3.  **Integración con servicios externos:** Asociarse con supermercados o servicios de entrega para comprar los ingredientes directamente desde la aplicación.

4.  **Cursos en directo con chefs influencers:** Dado a que esta es una aplicación que funge como una red social también, se pueden realizar contratos con los mejores chefs influencias y brindarles un apartado único en el que podría compartir y enseñar sus recetas en directo con sus seguidores.

5.  **Marketplace interno:** Crear un espacio para que los usuarios compren o vendan recetas destacadas, sus productos de ingredientes, utensilios, etc.

Cabe aclarar que muchas de estas estrategias son solo ideas hipotéticas y serían implmentadas a través de funciones que serían implentadas en versiones de la aplicación más avanzadas.
  
---

## **Impacto en los Usuarios**

CookBook es una aplicación que nació primeramente en la observación de una problemática reconocida en el entorno universitario, viendo como compañeros foráneos por escasos tiempos debido a sus estudios, éstos preferían comprar alimentos precocinados, congelados u optar por consumir tanto comida rápida como callejera.

Despues de varias charlas analizando esta problemática, se llegó a la hipótesis de que si estos alumnos por falta de tiempo para cocinar y carecnia de ingredientes para llevar a cabo esta actividad, tomarán la optativa de consumir sus alimentos de las maneras antes comentadas, lo cual es un gasto extra significativo y una se lleva a cabo una mala alimentación.

Este problema no solo afecta a los alumnos foráneos, si no que también afecta a una amplia variedad de usuarios, desde quienes buscan cocinar rápidas y económicas comidas hasta aquellos interesados en explorar nuevos estilos culinarios sin salir de casa, cuidando su dieta y gastando únicamente lo necesario para comer desde el hogar.

Estas son las problemáticas que pretende resolver CookBook, fomentando la alimentación saludable e impulsando la compra de ingredientes para elaborar las recetas en casa, generando un impacto sumamente positivo en los usuarios de CookBook dado a que gastarán menos comiendo fuera o comprando tanto alimentos congelados como precocinados y ya su vez gastando menos dinero.

El desarrollo de CookBook no solo aborda la problemática individual de planificar comidas con recursos limitados, sino que también contribuye a objetivos más amplios, como la reducción del desperdicio alimentario y la promoción de una buena alimentación. Además, al conectar a usuarios con estilos y culturas gastronómicas diversas, se fomenta el intercambio cultural y la creatividad culinaria.

---

## **Lenguaje de Programación**

La aplicación está desarrollada en **Kotlin**, ya que es un lenguaje moderno, seguro y eficiente soportado por Google, para el desarrollo de aplicaciones Android y su facilidad para integrar bibliotecas como Retrofit y Jetpack Compose.

Kotlin también es el lenguaje de programación más utlizado por desarrollaodres de Android alrededor del mundo.

---

## **Versión Mínima de Android**

La aplicación soportará dispositivos con **Android 8.0 (Oreo)** o versiones superiores, asegurando compatibilidad con la mayoría de los dispositivos modernos pero sin dejar atrás dispositivos que aún no son obsoletos.

---

## **Estructura Organizacional del Proyecto**

El proyecto **CookBook ** se organiza siguiendo las mejores prácticas de arquitectura y desarrollo en Android, utilizando el patrón de diseño **MVVM** (Model-View-ViewModel). Esto para asegurar una separación clara entre la lógica de negocio, los datos y la interfaces de usuario. A continuación una explicación de la estructura Organizacional del Proyecto:

<img src="https://lh7-rt.googleusercontent.com/docsz/AD_4nXdX1HxPpUPYwQfmh1ty2r4TKlCQvGWxnlELc05oZhOBvjQd6ThvhZ_vVxQB8HRCaQlry_MhlyKR1PLVAo2t42agOnMLT5DHt7VqNiBD4oTJCtnKKbajsg5E6i5oiWyrCRXNn2M2?key=1xsJWPuYS8PdR_aXOyAf5q7T" alt="dfioj" width="200"> <img src="https://lh7-rt.googleusercontent.com/docsz/AD_4nXfXkIKrfpUvWaVu1LlKVZC4iJkibDCuKyohbVFtA7zE5fSPvmtt0toYq16AuyH-zuwGwS1MWRT1imQ7BXzoXlY-LC7m-GVcFJOkYxtAcA2CRc-0i00kpCXPwAiQHVazn-7CrVtqKQ?key=1xsJWPuYS8PdR_aXOyAf5q7T" alt="Descripción de la imagen" width="210"> <img src="https://lh7-rt.googleusercontent.com/docsz/AD_4nXeI_U_CqAkV6yd0trK8yTKairp8Ej7RpH1OiaPEpY0WXpGinlniRqbtlxA0FJC-vglWI0gpUxDyJVxWXqU31fxpsskHXAFUDIF9jcYzIco6e7qgDDQacwLNldIjd4AFz6Du_LNcrw?key=1xsJWPuYS8PdR_aXOyAf5q7T" alt="Descripción de la imagen" width="210"> <img src="https://lh7-rt.googleusercontent.com/docsz/AD_4nXcQz7d2O_mADdm80sotsbVmF8vDIwm2UQgxRKEFUa16ZgcowFtgNTI5BhrxOSKqa-tkg1q9JzSxfVm7dbYQPPrbb8nXl5BWvY8SEWRRx8ZUqMrwmAF31TjMEvXDN-JhqUFOQ_6IVg?key=1xsJWPuYS8PdR_aXOyAf5q7T" alt="Descripción de la imagen" width="200"> <img src="https://lh7-rt.googleusercontent.com/docsz/AD_4nXcbjkMdIuOo8hTDiokRPFoLiPTWnNyR0YkOrm9SV1gcd6dCw_1iEGIJzWwYQ1Ww6Hs6tExlVcTL07qDOBqanhvWMEzJuTOMiFw0XIMt3oOyiHDAt6oRB5MnpdGqrDChahichLqDHQ?key=1xsJWPuYS8PdR_aXOyAf5q7T" alt="Descripción de la imagen" width="200"> <img src="https://lh7-rt.googleusercontent.com/docsz/AD_4nXe56a2evgu01yk8OrwqOS36xi0OUrEPjUrbWGTkq-zJ_zDKI_9LVXCqlikyKCnm61ga2-C_sy1DkWhvagFJI0qftC0-Vt08hn6F_BfMa8p8bhUgx_zyo1jrD5JyWVVlfbuB84vAoA?key=1xsJWPuYS8PdR_aXOyAf5q7T" alt="Descripción de la imagen" width="201">

### **Explicación**

#### **Directorio Principal (`app/`)**

Este directorio contiene las subcarpetas principales necesarias para el desarrollo de la aplicación.

---

#### **1. `manifests/`**

- **Archivo:** `AndroidManifest.xml`

- **Propósito:** Declara componentes de la aplicación como actividades, permisos requeridos, servicios, y configuraciones globales.

---

#### **2. `kotlin+java/`**

Este directorio contiene todo el código de la aplicación, organizado por paquetes.

##### **a. `navigation/`**

- **Propósito:** Maneja la navegación entre las pantallas de la aplicación.

- **Archivos principales:**

- `BarItem.kt`, `BottomNavBarView.kt`, `Routes`: Define las rutas y la navegación para la barra inferior.

- `MyAppNavigation.kt`: Contiene el proceso de navegación dentro de toda la aplicación.

##### **b. `network/`**

- **Propósito:** Configura las comunicaciones con el servidor.

- **Archivos principales:**

- `ApiService`: Define los endpoints de la API.

- `RetrofitClientInstance`: Configura y proporciona instancias de Retrofit para solicitudes HTTP.

##### **c. `notifications/`**

- **Propósito:** Maneja las notificaciones push.

- **Archivo principal:** `MyFirebaseMessagingService`: Configura Firebase Cloud Messaging para recibir notificaciones.

##### **d. `preferences/`**

- **Propósito:** Almacena preferencias del usuario y datos persistentes utilizando DataStore.

- **Archivo principal:** `UserPreferences.kt`: Contiene la persistencia de datos como tokens y configuraciones del usuario.

---

#### **3. `presentation/`**

Contiene la lógica y las vistas relacionadas con cada funcionalidad de la aplicación, dividida por módulos.

##### **a. `addrecipe/`**

- **Propósito:** Espacio para la creación de recetas.

- **Subcarpetas:**

- `models/`: Contiene clases de datos como `RecipeBody.kt` y `RecipeResponse.kt`.

- `network/`: Incluye `RecipeBodyRepository.kt` para gestionar las interacciones con la API.

- `viewmodels/`: Incluye `AddRecipeViewModel.kt` para la lógica de agregar una receta.

- `views/`: Contiene `AddRecipeView.kt`, la interfaz gráfica para crear recetas.

##### **b. `finder/`**

- **Propósito:** Sistema de búsqueda de recetas.

- **Subcarpetas:**

- `models/`: Define estructuras como `SearchBody.kt` y `SearchResponse.kt`.

- `network/`: Gestiona las solicitudes de búsqueda mediante `FinderBodyRepository.kt`.

- `viewmodels/`: Lógica para búsqueda de recetas, incluyendo `SpecifiedFinderViewModel.kt`.

- `views/`: Interfaces de búsqueda como `InitialSearch.kt` y `SearchView.kt`.

##### **c. `home/`**

- **Propósito:** Pantalla principal con recetas organizadas por categorías.

- **Subcarpetas:**

- `models/`: Estructuras como ***HomeRecipeody.kt***

- `network/`: Gestión de solicitudes mediante ***HomeRepository.kt***.

- `viewmodels/`: Lógica de vista con ***HomeViewModel.kt***.

- `views/`: Interfaz gráfica con `HomeView.kt`.

##### **d. `login/`**

- **Propósito:** Módulo para iniciar sesión.

- **Subcarpetas:**

- `models/`: Clases como `LoginBody.kt` y `LoginResponse.kt`.

- `network/`: Comunicación API mediante `LoginBodyRepository.kt`.

- `viewmodels/`: Lógica de inicio de sesión con `LoginViewModel.kt`.

- `views/`: Pantallas como `LoginView.kt` y `ForgotPasswordView.kt`.

##### **e. `signup/`**

- **Propósito:** Registro de nuevos usuarios.

- **Subcarpetas:**

- `models/`: Datos como `SignupBody.kt` y `SignupResponse.kt`.

- `network/`: Solicitudes API con `SignupBodyRepository.kt`.

- `viewmodels/`: Lógica de registro de usuarios con `SignupViewModel.kt`.

- `views/`: Interfaz gráfica como `SignupView.kt`.

##### **f. `user/`**

- **Propósito:** Informacion del perfil del usuario, como sus recetas y listas creadas así como el historial de recetas vistas.

- **Subcarpetas:**

- `models/`: Clases como `UserDetailsBody.kt` y `UserDetailsResponse.kt`.

- `network/`: Gestión API mediante repositorios como `UserBodyRepository.kt`.

- `viewmodels/`: Lógica de vista de usuario con `UserDetailsViewModel.kt`.

- `views/`: Pantallas como `UserView.kt` y `ProfilePictureSelectorView.kt`.

##### **g. Otros módulos:**

- **`onboarding/`**: Guía inicial para nuevos usuarios.

- **`recipe/`**: Vista de detalles de recetas.

- **`lists/`**:  Vista de listas creadas por le usuario y las recetas que contiene dentro.

- **`title/`**: Pantalla de bienvenida.

---

#### **4. `ui/`**

- **Propósito:** Maneja el diseño y la personalización de la interfaz de usuario.

- **Subcarpetas:**

- `theme/`: Define colores, tipografías y temas en archivos como `Color.kt` y `Theme.kt`.

---

#### **5. `res/`**

- **Propósito:** Contiene recursos estáticos.

- **Subcarpetas:**

- `drawable/`: Imágenes y gráficos.

- `values/`: Archivos XML con cadenas, estilos y colores.

---

#### **6. `Gradle Scripts/`**

- **Propósito:** Maneja la configuración del proyecto.

- **Archivos importantes:**

- `build.gradle.kts`: Configuración de dependencias y plugins.

- `proguard-rules.pro`: Reglas de optimización y ofuscación para la compilación.

---

#### **7. `MainActivity.kt`**

- **Propósito:** Punto de inicio de la aplicación. Configura la navegación inicial y el tema general.

---

## **Patrón de Diseño**

Se implementa el patrón de diseño **MVVM** (Model-View-ViewModel), para separar las responsabilidades y tareas como tambien mejorar la escalabilidad del proyecto.

**Diagrama de Secuencia**

Ejemplo: Proceso de agregar una receta.

1. El usuario completa los datos de agregar receta en la vista.

2. La vista llama un método en el ViewModel para procesar los datos.

3. El ViewModel valida los datos y llama al repositorio.

4. El repositorio envía los datos al servidor.

5. El servidor almacena los datos en MongoDB.

6. Se envía una respuesta al cliente confirmando el éxito.

![](https://lh7-rt.googleusercontent.com/docsz/AD_4nXeZvOdfyr8Dx27eNLjcQgWPNMi2v1KbcdOXOI0SXYSojD0ctJeU7_S0koK2zLL0HE0HpVXgg9I3rSXg0la-nuvEsD155VB1fYtUWBp-291K9MqAjuuGoV1OqiDOycobWAwXppCyPg?key=1xsJWPuYS8PdR_aXOyAf5q7T)

---

## **Herramienta para la Creación de Vistas**

La interfaz de usuario se desarrolla con **Jetpack Compose**, una biblioteca declarativa de Google que permite construir vistas tanto modernas como dinámicas con uso de menos código.

---

## **Arquitectura Cliente-Servidor**

La aplicación interactúa con un servidor backend en donde el cliente (Android), realiza solicitudes HTTP utilizando Retrofit. El backend está desarrollado con Node.js y MongoDB como base de datos.

**Componentes principales de la arquitectura:**

1. Cliente: La aplicación móvil desarrollada en Android Studio utilizando Kotlin, que implementa MVVM. El cliente interactúa con el servidor para obtener y enviar datos a través de la API.

2. Servidor: Desarrollado en Node.js con Express.js, el servidor actúa como intermediario entre la aplicación y la base de datos.

3. Base de Datos: MongoDB se utiliza como base de datos principal para almacenar recetas, información de usuarios y preferencias.

---

## **Verbos Comunes del Protocolo HTTP**

**Verbos usados en Cookbook:**

- **GET**: Sirve para obtener datos del servidor.

- **Ejemplo en CookBook:**

- Para obtener todas las categorías:

`GET /get-all-categories`

Respuesta:

```json

{ "id":"60d21b4867d0d8992e610c89",

"category": "Postres"

},

{ "id":"60d21b4967d0d8992e610c8a",

"category":"Americana"

}

```

- **POST**: Enviar datos al servidor para que sean procesados o almacenados. 
A pesar de no ser la manera convencional de manejar servicios, los servicios de `DELETE` también se elaboraron usando como acción `POST`.

- **Ejemplo en CookBook:**

- Crear un ingrediente:

`POST /create-ingredient Content-Type: application/json`

Cuerpo de la solicitud:

```json

{ "nameIngredient": "Tomate",

"category": "60d21b4867d0d8992e610c89"

}

```
Respuesta:

```json

{ "status": "success",

"message": "Ingrediente creado exitosamente",

"id": "671a95f3be2185ee8bb6b1f5"

}

```

- **Ejemplo en CookBook:**

- Eliminar una receta:

`POST /delete-recipe Content-Type: application/json`

Cuerpo de la solicitud:

```json

{

"id": "671a95f3be2185ee8bb6b1f5"

}

```

Respuesta:

```json

{

"status": "success",

"message": "Receta eliminada correctamente",

}

```
**Verbos NO usados en CookBook:**

- **DELETE**: Eliminar datos específicos en el servidor. Como se mencionó antes, a pesar de sí exisiter acciones de este tipo en la aplicación se manejaron por medio de `POST`.

- **PUT**: Actualizar datos existentes en el servidor.

- **SEARCH**: Filtrar o buscar datos en el servidor basados en criterios específicos.

---

## **Estatus de Respuesta del Servidor**

- **200 OK**: La solicitud fue procesada exitosamente.

- Ejemplo: Cuando se obtienen todas las recetas en la pantalla Home.

- **400 Bad Request**: Error en la solicitud enviada por el cliente.

- Ejemplo: Campos obligatorios faltantes en una solicitud `POST` como agregar una receta.

- **401 Unauthorized**: El usuario no está autenticado.

- Ejemplo: Intentar acceder a una funcionalidad protegida sin un token válido o expirado.

- **404 Not Found**: El recurso solicitado no existe.

- Ejemplo: Solicitar un ingrediente con un `id` inexistente.

- **500 Internal Server Error**: Error inesperado en el servidor.

- Ejemplo: Fallo en la conexión con el servidor.

---

## **Características Técnicas**

### Base64

- **¿Para qué sirve?**

Base64 es un esquema de codificación utilizado para convertir datos binarios en un formato de texto plano. Esto facilita la transferencia de datos a través de medios que solo soportan texto, como imagenes de perfil de usuario, correos electronicos o datos JSON en APIs.

#### **Notas importantes sobre Base64**

1. **Eficiencia**: Base64 aumenta el tamaño de los datos en aproximadamente un 33%. Esto significa que una imagen codificada será más grande que la original.

2. **Limitaciones**: Debido al aumento en tamaño, no se recomienda para archivos grandes. En su lugar, se deben considerar soluciones como almacenamiento en la nube (por ejemplo Cloudinary) y solo enviar la URL del archivo.

3. **Decodificación en el backend**: Una vez que los datos llegan al servidor, es necesario decodificarlos para usarlos.

### Data Class

**¿Qué es?**

Una Data Class es una clase especial en Kotln diseñada específicamente para almacenar datos. Proporciona una forma eficiente de definir clases que solo contienen información, reduciendo la cantidad de código necesario.

Cuenta con un constructor primario que es obligatorio, el cual definirá cada propiedad de la clase en cuestion.

Los Data Class son fundamentales ya que nos ayudan a la creación de los metodos que se utilizan en la aplicación, facilitando su manejo.

Tambien cabe destacar que son imprescindibles al momento de trabajar con API's o con bases de datos para manejar cuerpos y respuestas del servidor.

**Ejemplo:**

```kotlin

data class RecipeBody(

val nameRecipe: String,

val preptime: String, val ingredients: List<Ingredient>,

val steps: List<String>,

val categoria: String,

val autor: String

)

```

**Uso:**

- Facilita el manejo de datos como las recetas (`RecipeBody`), ingredientes (`Ingredient`), y usuarios.

- Permite crear, copiar, y comparar instancias fácilmente:

```kotlin

val recipe = RecipeBody("Sandwich", "10 min", listOf(...), listOf(...), "Sandwiches", "Chef Juan")

val updatedRecipe = recipe.copy(preptime = "15 min")

```

### Navegación

La navegación en una aplicación movil es el mecanismo que permite al usuario moverse entre las diferentes pantallas o vistas. Esto incluye transiciones entre funciones, como pasar del inicio de sesión al perfil del usuario o de la búsqueda a la pantalla de los resultados.

- **¿Cómo funciona?**

En CookBook, la navegación utiliza **Jetpack Navigation Component**, que permite gestionar de manera eficiente las transiciones entre pantallas mediante un `NavHost` (contenedor de rutas y vistas navegables) y rutas predefinidas.

#### **Componentes principales:**

1. **`NavController`:** Controla y gestiona las rutas entre las vistas.

2. **`NavHost`:** Contenedor que define las pantallas y su flujo de navegación.

3. **`Rutas`:** Definidas en `Routes.kt` para un acceso centralizado y consistente.

#### **Ejemplo del Código de Navegación:**

1. **Definición de Rutas (`Routes.kt`):**

```kotlin

object Routes {

const val LoginView = "LoginView"

const val HomeView = "HomeView"

const val AddRecipeView = "AddRecipeView"

}

```

  

2. **Configuración de Navegación (`MyAppNavigation.kt`):**

  

```kotlin

@Composable

fun MyAppNavigationView() {

val navController = rememberNavController()

NavHost(navController = navController, startDestination = Routes.LoginView) {

composable(Routes.LoginView) { LoginView(navController) }

composable(Routes.HomeView) { HomeView(navController) }

composable(Routes.AddRecipeView) { AddRecipeView(navController) }

}

}

```

3. **Navegación entre Pantallas:** Desde un botón o evento:

```kotlin

Button(onClick = { navController.navigate(Routes.HomeView) }) {

Text("Go to Home")

}

```

### SharedPreferences

#### **¿Qué es?**

**SharedPreferences** es una herramienta nativa de Android utilizada para almacenar datos clave-valor de forma persistente en el dispositivo. Es ideal para guardar configuraciones, preferencias del usuario o pequeños datos que necesitan mantenerse entre sesiones, como tokens de autenticación o el estado de inicio de sesión.

Los datos almacenados en **SharedPreferences** son:

- **Ligados a la aplicación:** Solo son accesibles dentro del contexto de la misma.

- **Persistentes:** Permanecen disponibles incluso después de cerrar la aplicación, a menos que sean eliminados explícitamente.

#### **¿Cómo se usa?**

En el proyecto, se utiliza **Jetpack DataStore**, que es una mejora de **SharedPreferences**, para almacenar preferencias clave como el token del usuario.

```kotlin

// Configuración de DataStore

val Context.dataStore by preferencesDataStore(name = "user_preferences")

object UserPreferencesKeys {

val TOKEN = stringPreferencesKey("token")

}

// Guardar datos en DataStore

suspend fun saveToken(context: Context, token: String) {

context.dataStore.edit { preferences ->

preferences[UserPreferencesKeys.TOKEN] = token

}

}

// Recuperar datos desde DataStore

fun getToken(context: Context): Flow<String?> {

return context.dataStore.data.map { preferences ->

preferences[UserPreferencesKeys.TOKEN]

}

}

```

#### **Desglose del ejemplo:**

1. **Guardar un token:**

- `saveToken` toma un contexto y un token como parámetros.

- El método `edit` modifica las preferencias almacenadas, guardando el token bajo la clave definida en `UserPreferencesKeys`.

2. **Recuperar un token:**

- `getToken` retorna un flujo (`Flow`) que permite observar cambios en el token almacenado.

- Esto asegura que cualquier actualización en el valor sea reflejada automáticamente.

#### **Ventajas de utilizar SharedPreferences/DataStore:**

1. **Simplicidad:** Ideal para almacenar datos pequeños, como configuraciones o credenciales.

2. **Persistencia:** Los datos permanecen disponibles entre sesiones de usuario.

3. **Integración:** Fácil de implementar con las herramientas nativas de Android y modernas como DataStore.

En **CookBook**, el uso de **DataStore** en lugar de **SharedPreferences** asegura una gestión de datos más eficiente y moderna, alineada con las mejores prácticas de desarrollo Android.

### Permisos de la aplicación

Los permisos en Android son requerimientos que la aplicación solicita al usuario para acceder a funcionalidades del dispositivo, como la cámara, almacenamiento, ubicación o internet.

#### **Permisos utilizados:**

En la aplicación **CookBook**, se declararon y usaron los siguientes permisos clave:

1. **Permiso para acceso a internet (`INTERNET`)**: Necesario para realizar solicitudes HTTP a la API, como obtener recetas, categorías y otros datos en tiempo real.

2. **Permiso para notificaciones (`POST_NOTIFICATIONS`)**: Permite a la aplicación enviar notificaciones push al dispositivo del usuario, informándole sobre actualizaciones o recordatorios.

3. **Permiso para recibir eventos de arranque (`RECEIVE_BOOT_COMPLETED`)**: Usado para reanudar servicios como notificaciones después de que el dispositivo se reinicie.

4. **Permiso para acceder a la galería (`READ_EXTERNAL_STORAGE`)**: Permite al usuario seleccionar imágenes desde su dispositivo, por ejemplo, al agregar fotos de recetas.

#### **¿Cómo se agregan?**

1. **Declaración en el archivo `AndroidManifest.xml`:**

Los permisos se declararon para que el sistema Android los reconozca, esto en la carpeta `app/manifests/`.

  

**Ejemplo de permisos en `AndroidManifest.xml`:**

```xml

<uses-permission android:name="android.permission.INTERNET" />

<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />

<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />

<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

```

- **`android.permission.INTERNET`:** Habilita la comunicación con la API y la carga de datos.

- **`android.permission.POST_NOTIFICATIONS`:** Permite el envío de notificaciones al usuario.

- **`android.permission.RECEIVE_BOOT_COMPLETED`:** Reactiva servicios al reiniciar el dispositivo.

- **`android.permission.READ_EXTERNAL_STORAGE`:** Permite al usuario seleccionar imágenes almacenadas en el dispositivo.

2. **Solicitud de permisos en tiempo de ejecución:**

Para permisos peligrosos como el acceso a la galería, se solicitó la aprobación del usuario en tiempo de ejecución.

3. **Manejo de la respuesta del usuario:**

- Si el usuario otorga el permiso, se habilita la funcionalidad asociada (como abrir la galería).

- Si el permiso es denegado, se notifica al usuario que no podrá acceder a ciertas funciones.

4. **Probar el funcionamiento de los permisos:**

- Verifica que la aplicación pueda realizar conexiones a internet para cargar datos o enviar solicitudes.

- Asegúrate de que el acceso a la galería y las notificaciones funcionen correctamente en dispositivos reales.

---

## **Generación del APK**

La generación del APK en Android Studio incluye configuraciones para firmar la aplicación con claves, asegurando su autenticidad y habilitando el despliegue en Google Play Store.

### **Pasos para Generar el APK**

1. **Abrir la opción de generación:**

- En Android Studio, ve a **`Build > Build Bundle(s)/APK(s) > Build APK(s)`**.

2. **Seleccionar el tipo de construcción:**

- Elige si deseas generar un APK de `debug` o de `release`.

3. **Configurar la firma (solo para `release`):**

- Proporciona el archivo `keystore`, el alias de la clave y la contraseña.

- Configura esta información en el archivo `build.gradle` del módulo, si no está previamente definida.

4. **Generar el APK:**

- Android Studio compilará el proyecto y generará el APK en la carpeta `app/build/outputs/apk/`.

5. **Probar el APK:**

- Antes de distribuirlo, pruébalo en un dispositivo real para asegurarte de que funcione correctamente.

6. **Distribuir:**

- Puedes compartir el APK manualmente o publicarlo en Google Play Store siguiendo sus políticas y configuraciones adicionales.

### **Importancia en CookBook**

La generación del APK permite empaquetar la aplicación **CookBook** en un formato ejecutable para dispositivos Android, asegurando que cumpla con los estándares de distribución y seguridad. Esto facilita que los usuarios puedan instalar y utilizar la aplicación directamente desde un enlace de descarga o desde Google Play Store.

---

## **Librerías Utilizadas**

- **Retrofit**: Utilizada para manejar solicitudes HTTP de manera eficiente y escalable. Facilita la interacción con APIs RESTful y la conversión de JSON a objetos Kotlin mediante su conversor Gson.

- **OkHttp**: Cliente HTTP para Android que gestiona las conexiones de red de forma eficiente. Incluye el log `logging-interceptor` para registrar y depurar las solicitudes y respuestas de la red.

- **Jetpack Compose**: Herramienta de Google para desarrollar interfaces gráficas modernas con un enfoque declarativo. Incluye módulos como `material3` para componentes visuales y `ui-tooling-preview` para previsualizar el diseño en tiempo real aplicada en algunas vistas.

- **Firebase Messaging**: Proporciona funcionalidad para notificaciones push, permitiendo comunicación en tiempo real entre la aplicación y los usuarios.

- **Coil (Coil-Compose)**: Librería para la carga de imágenes en aplicaciones Android, optimizada para Jetpack Compose.

- **Lottie Compose**: Permite integrar animaciones Lottie en aplicaciones Android diseñadas con Jetpack Compose.

- **DataStore Preferences**: Almacena datos clave-valor de manera eficiente y segura, reemplazando el uso de `SharedPreferences`.

- **JWTDecode**: Proporciona herramientas para decodificar y verificar tokens JWT (JSON Web Tokens), mejorando la seguridad en la autenticación de usuarios.

- **Navigation Compose**: Maneja la navegación entre pantallas en aplicaciones Jetpack Compose, asegurando transiciones fluidas y estructuradas.

- **Material Icons Extended**: Proporciona un conjunto ampliado de íconos que se pueden usar directamente en interfaces desarrolladas con Jetpack Compose, utilizadas principalmente en la vista de home para ingredientes.

Estas librerías están configuradas en el archivo **`build.gradle (Module: app)`**, específicamente dentro del bloque `dependencies {}`.

---

## **Debugging y Logs**

#### ¿Qué es el Debugging y cómo se usan los Logs?

El debugging es el proceso de identificar, analizar y solucionar errores en el código de una aplicación. En CookBook, el debugging se implementa mediante bloques `try-catch` para manejar errores y mediante logs que permiten registrar eventos importantes durante la ejecución. Los logs son mensajes que facilitan el seguimiento del comportamiento de la aplicación y ayudan a diagnosticar problemas rápidamente.

#### Tipos de Logs

Los logs se clasifican en niveles según su propósito:

1. **Log.d (Debug)**: Se usa para mensajes de depuración que ayudan durante el desarrollo.

- Ejemplo: `Log.d("TAG", "Mensaje de depuración")`

2. **Log.i (Info)**: Registra información útil, como estados del sistema o acciones exitosas.

- Ejemplo: `Log.i("TAG", "Operación completada con éxito")`

3. **Log.w (Warning)**: Notifica sobre posibles problemas que no detienen el sistema.

- Ejemplo: `Log.w("TAG", "La conexión es inestable")`

4. **Log.e (Error)**: Registra errores que requieren atención inmediata.

- Ejemplo: `Log.e("TAG", "Error crítico: ${exception.message}")`

#### Ejemplos de Archivos en los que se Utilizaron Debugs y Logs

1. **LoginViewModel.kt**

- **Motivo:** Gestionar el proceso de inicio de sesión, incluyendo el registro del token generado tras un inicio exitoso, se implementa a travez de un bloque de try-catch.

- **Uso de log:**

- `Log.d`: Para registrar el token que se guarda tras un inicio de sesión exitoso.

**Script:**

```kotlin

fun doLogin(username: String, password: String) {

isLoading = true

viewModelScope.launch {

try {

loginResponse = loginbodyRepository.doLogin(LoginBody(username, password))

val token = loginResponse.token

saveToken(appContext, token)

Log.d("Token", "Nuevo token guardado: $token")

loginResponse.message = "Login exitoso"

loginResponse.isSuccess = true

} catch (exception: Exception) {

loginResponse.message = "Error en el login"

loginResponse.isSuccess = false

} finally {

isLoading = false

}

}

}

  

```

**Fragmento de Código:**

```kotlin

Log.d("Token", "Nuevo token guardado: $token")

```

2. **GetRecipeViewModel.kt**

- **Propósito:** Manejar errores al recuperar información de recetas y registrar mensajes de error.

- **Log Utilizado:**

- `Log.e` para registrar excepciones al buscar recetas.

- **Fragmento de Sript:**

```kotlin

fun getRecipe(recipeId: String) {

viewModelScope.launch {

isLoading = true

try {

val token = getToken(appContext).firstOrNull()

if (!token.isNullOrEmpty()) {

val response = getRecipeBodyRepository.getRecipe(

getRecipeBody = GetRecipeBody(id = recipeId, userId = userId),

token = token

)

recipe = response

} else {

message = "Token not found"

}

} catch (e: Exception) {

Log.e("GetRecipe", "Error fetching recipe: ${e.message}")

message = "Error fetching recipe: ${e.message}"

} finally {

isLoading = false

}

}

}

```

3. **RetrofitClientInstance.kt**

- **Propósito:** Capturar las solicitudes HTTP para depuración.

- **Log Utilizado:**

- **`HttpLoggingInterceptor:`** El propósito del **HttpLoggingInterceptor** es capturar y registrar detalles de las solicitudes y respuestas HTTP para facilitar la depuración y el monitoreo del rendimiento en las interacciones con el servidor. Esto permite validar que las solicitudes cumplen con los requisitos del backend y ayuda a diagnosticar problemas en entornos de desarrollo y producción.

- **Fragmento del Script:**

```kotlin

val httpLoggingInterceptor = HttpLoggingInterceptor().apply {

level = HttpLoggingInterceptor.Level.BODY

}

val okHttpClient = OkHttpClient.Builder()

.addInterceptor(httpLoggingInterceptor)

.build()

```

#### Ejemplo de Uso de try-catch

**Archivo: `AddRecipeViewModel.kt`**

- **Propósito:** El `try-catch` asegura la gestión adecuada de excepciones durante la creación de recetas, como errores de red o problemas de autenticación, brindando retroalimentación clara al usuario. También permite mantener el estado de la vista actualizado, evitando bloqueos en la aplicación, y registra errores relevantes para que los desarrolladores puedan identificarlos y resolverlos con mayor facilidad, mejorando la estabilidad y experiencia de usuario.

- **Fragmento de Código:**

  

```kotlin

  

fun createRecipe(

nameRecipe: String,

description: String,

preptime: Number,

...

) {

viewModelScope.launch {

----> try {

val token = getToken(appContext).firstOrNull() ?: throw IllegalStateException("Token no disponible")

val createdDate = getCurrentDate()

recipeResponse = recipeBodyRepository.createRecipe(

RecipeBody(

nameRecipe,

description,

preptime,

ingredients,

steps,

createdDate,

category,

userId,

image,

video,

grade

),

token

)

state = 1

recipeResponse.message = "Receta creada de forma exitosa"

recipeResponse.isSuccess = true

----> } catch (exception: Exception) {

Log.e("CreateRecipe", "Error en la creación de la receta: ${exception.message}") //Aqui se implementó tambien un exception log

recipeResponse.message = "Error en la creación de la receta: ${exception.message}"

recipeResponse.isSuccess = false

}

}

}

```

---

## **Firebase Cloud Messaging**

#### **¿Qué es?**

Firebase Cloud Messaging (FCM) es un servicio gratuito proporcionado por Firebase que permite enviar mensajes y notificaciones push a dispositivos Android, iOS y aplicaciones web. Este servicio es ideal para mantener a los usuarios informados sobre actualizaciones importantes, recordatorios o mensajes personalizados.

#### **¿Cómo funciona?**

1. **Configuración en Firebase Console:**

- El proyecto de la aplicación se registra en Firebase Console.

- Se descargan las credenciales necesarias (como el archivo `google-services.json` para Android).

2. **Integración en la Aplicación:**

- Se agrega la dependencia de Firebase en el archivo `build.gradle`.

- La aplicación se configura para manejar las notificaciones utilizando el servicio `FirebaseMessagingService`.

3. **Generación de Tokens:**

- Cada dispositivo registrado recibe un **token único** que identifica la instancia de la aplicación.

- Este token se utiliza para direccionar mensajes a dispositivos específicos.

4. **Envío de Mensajes:**

- Los mensajes pueden ser enviados directamente desde Firebase Console o mediante el servidor backend utilizando la API de FCM.

- Existen dos tipos principales de mensajes:

- **Mensajes de notificación:** Se muestran automáticamente en el sistema de notificaciones del dispositivo.

- **Mensajes de datos:** Permiten personalizar el contenido del mensaje y cómo se procesa en la aplicación.

5. **Recepción de Mensajes:**

- La aplicación implementa un servicio extendiendo `FirebaseMessagingService`.

- Los mensajes recibidos se manejan en el método `onMessageReceived`.

----------

#### **Importancia en la app**

En CookBook, Firebase Cloud Messaging se puede usar para:

- Notificar a los usuarios sobre nuevas recetas disponibles.

- Informar sobre actualizaciones de la aplicación o cambios relevantes en sus listas de recetas.

---

## **Archivo Scratch**

Los archivos Scratch en Android Studio son documentos temporales creados para tomar notas, realizar pruebas de código rápidas o almacenar fragmentos reutilizables sin afectar el proyecto principal. En CookBook, estos se usan para:

- Documentar ideas rápidas para funcionalidades futuras.

- Probar pequeñas porciones de código o esquemas sin integrarlas directamente.

---


