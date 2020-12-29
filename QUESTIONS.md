# PARTE TEORICA

### Arquitecturas de UI: MVP, MVVM y MVI

#### MVVM

##### ¿En qué consiste esta arquitectura?
MVVM es el acrónimo de Model View ViewModel y es un patrón de diseño que consta de los siguientes componentes de software:
- Model: Es el componente de gestión de datos. Es el responsable de definir los tipos de datos y editarlos, guardarlos o recuperarlos desde la memoria del dispositivo o una base de datos. Proporciona los datos al ViewModel y recibe eventos de este para modificar los datos.
- View: Es el componente de visualización. Es el responsable de mostrar la información a través de la interfaz gráfica y de atender las interacciones con el usuario. En Android este componente sería una activity o fragment.
- ViewModel. Es el responsable de trasladar la información almacenada en el modelo a la vista y también de proporcionar enlaces a la vista para pasarle eventos al modelo, de forma que, si la vista actualiza un valor en pantalla, el modelo se actualiza automáticamente.
En este patrón de diseño, el ViewModel se mantiene en memoria mientras el objeto al que pertenece no se haya terminado, lo cual permite responder correctamente a los cambios de configuración o ciclo de vida de la aplicación, como cambios de orientación de pantalla.

##### ¿Cuáles son sus ventajas?
La principal ventaja de este patrón es que es el patrón recomendado por Google y la arquitectura ya nos proporciona la clase ViewModel y herramientas para trabajar con el ciclo de vida de las actividades. Además, el model y view quedan completamente desacoplados, lo cual simplifica el mantenimiento y testing.

##### ¿Qué inconvenientes tiene?
Como desventaja podemos comentar que, al trabajar con los datos de forma más automática, es necesario el uso de otros componentes de data binding como LiveData, RXJava o RXAndroid para que los datos se actualicen en tiempo real. Además, su implementación normalmente implica crear más archivos o clases que en otros modelos

#### MVP

##### ¿En qué consiste esta arquitectura?
MVP es el acrónimo de Model View Presenter y es un patrón de diseño muy similar al MVVM. Consta de los componentes Model y View, con la misma funcionalidad que en el patrón MVVM y cambia el ViewModel por el componente presenter:
- Presenter: Es el componente que actúa como conexión entre el modelo y la vista. En este componente añadiríamos cualquier código que no esté relacionado con gestión de datos o interfaz de usuario. El presentador actúa siempre como intermediario para que vista y modelo no tengan que interactuar directamente entre ellas: Recupera datos del modelo y los pasa para mostrarlos en la vista, y procesa las acciones de usuario enviadas por la vista para actualizar los datos.

##### ¿Cuáles son sus ventajas?
La ventaja de este patrón estaría en que es un poco más sencillos de implementar, la separación de código queda mas clara con lo cual es más fácil de testear y debugar.

##### ¿Qué inconvenientes tiene?
Una desventaja de este patrón es que por cada activity o fragment en nuestro código deberíamos tener un presenter, lo cual puede complicar la implementación dependiendo de la variedad de métodos a implementar. Además, Android no nos ofrece de forma nativa la posibilidad de desarrollar bajo este patrón, aunque sigue siendo una de las soluciones más populares

#### MVI

##### ¿En qué consiste esta arquitectura?
MVP es el acrónimo de Model View Intent. Este patrón de diseño es significativamente diferente a los ejemplos anteriores. Los roles que hay definidos en este patrón son los siguientes:
- Model: Es el componente de gestión de datos y además representa los diferentes estados de la aplicación, como puede ser visualización de datos, estado de descarga de datos, etc…
-  View: Es el componente de visualización y se encarga de actualizar la información en pantalla a través de interficies y en función del estado del Model.
- Intent: Un intent es la intención o deseo de un usuario para realizar una acción, lo cual provoca un cambio en el estado del model, el cual provoca de nuevo un cambio en la interfaz.

##### ¿Cuáles son sus ventajas?
La ventaja de este modelo es que tenemos una única fuente de datos, el modelo, lo cual evita mantener los diferentes estados en los diferentes componentes de código. Además, el flujo de trabajo es unidireccional y cíclico.

##### ¿Qué inconvenientes tiene?
La principal desventaja es que se trata de un patrón relativamente nuevo, y por tanto hay menos información disponible y proyectos contrastados respecto a otros modelos. Además, al igual que el patrón MVP, no esta implementado en Android de forma nativa y por tanto debemos manejar el ciclo de vida con nuestro código.

---

### Testing

#### ¿Qué tipo de tests se deberían incluir en cada parte de la pirámide de test? Pon ejemplos de librerías de testing para cada una de las partes. 
Una parte importante del desarrollo de cualquier aplicación son los tests. Según el nivel de complejidad de la aplicación, el usuario va a poder realizar multitud de acciones en la aplicación, y aunque se prueben personalmente cada una de ellas, la evolución y los cambios realizados durante el desarrollo hacen inviable dedicar tiempo continuamente a realizar las pruebas manualmente. Por ellos es necesario mantener un entorno de pruebas automatizado que se pueda ejecutar periódicamente o a petición.
Podemos distinguir entre diversos tipos de test:
-Nivel inferior: Las pruebas unitarias son pruebas para validar el funcionamiento de una clase y se pueden probar incluso sin dispositivos físicos ni emuladores. Son los tests que se pueden realizar con mayor velocidad y están pensados para hacer test a nivel de código. Para estos test se debe configurar el entorno con objetos mock para probar que con unos datos de entrada concretos obtenemos los resultados deseados. En Android disponemos de herramientas con Junit o Mockito para este tipo de test.
-Nivel intermedio: Los test de integración se usan para validar las interacciones entre el Código de nuestra aplicación y el entorno Android. Estos test se pueden ejecutar en emuladores, dispositivos físicos, o en bancos de pruebas que nos permiten simular el comportamiento en multitud de dispositivos con, por ejemplo, diferentes configuraciones de pantalla.
-Nivel superior: Los test de nivel superior o test de interfaz gráfica son pruebas que nos permiten emular el comportamiento del usuario y comprobar el resultado de estas acciones en la interficie grafica. En Android disponemos de herramientas como Espresso o UIAutomator para realizar este tipo de tests.
La pirámide de test de Android es una representación visual de como deberíamos repartir el numero tests a implementar en función de la fidelidad a la aplicación final. Es conveniente que las pruebas unitarias sean mas numerosas que los test de integración, y estos mas numerosos que los test de UI. Aunque los porcentajes pueden variar en función de la aplicación, una buena recomendación de porcentajes en cada categoría seria 70% en nivel inferior, 20% en nivel intermedio, y 10% en nivel superior.

#### ¿Por qué los desarrolladores deben centrarse sobre todo en los Unido Tests?
Los desarrolladores se deben centrarse en los tests unitarios ya que estos test evalúan la base de la aplicación y validan la funcionalidad de cada clase de dentro de la aplicación. Además son los test más rápidos de ejecutar y es una buena practica implementar los test al mismo tiempo que se desarrolla el código. Además estos test evalúan únicamente el código, y son independientes del aspecto final de la aplicación, por lo que no se depende de otros departamentos (diseño gráfico, marketing,…)

---

### Inyección de dependencias

#### Explica en qué consiste y por qué nos ayuda a mejorar nuestro código.
Las dependencias de código ocurren cuando una clase requiere de una referencia a un objeto de otra clase. Este objeto se puede obtener de diferentes formas: lo puede crear dentro del código de la clase, lo puede obtener de un objeto creado en otro punto de código, o lo puede recibir como parámetro. La primera opción, en general, no es una buena práctica, ya que si la clase responsable de usar un objeto es responsable también de crearlo, tendremos dificultades para reutilizar ese código y testearlo por separado. Si se recibe el objeto como parámetro estamos usando inyección de dependencias. 

#### Explica cómo se hace para aplicar inyección de dependencias de forma manual a un proyecto (sin utilizar librerías externas).
La inyección se puede realizar pasando el objeto en el constructor de la clase, o usando una variable tipo lateinit y una función pública para setear el valor.
Para aplicar la inyección de dependencias únicamente es necesario crear los objetos que necesitemos fuera de la clase, y pasarlos como parámetro. No obstante, esto es bastante simple si únicamente tenemos una dependencia. En el caso de que tengamos varias dependencias, por ejemplo, una clase necesita de un objeto, que a su vez necesita de otro objeto, tendremos que ir creando los objetos y pasándolos como parámetro uno a uno y en el orden correcto.
En el caso de que la aplicación necesite de dependencias mas complicadas, podemos recurrir a algún framework de gestión de dependencias como Koin, Proton o Dagger o Guice.

---

### Análisis de código estático 

#### Haz una lista con 5 warnings reportados por la herramienta Lint y explica de qué problema te avisan y cómo corregirlos.

Android->Lint->Accesibility->Image without content Description
Este aviso nos indica que tenemos definidos ImageViews en algún layout de la aplicación sin describir su contenido. Se puede corregir fácilmente añadiendo un valor al parámetro android:contentDescription

Android->Lint->Correctness->Obsolete Gradle Dependency
Este aviso nos indica que hay una versión más actual de alguna de las librerías definidas en el gradle de la aplicación. Clickando en el botón derecho sobre el warning tenemos la posibilidad de cambiar a la última versión disponible.

Android->Lint->Security-> Using setJavaScriptEnabled
Este aviso nos indica que hemos activado Javascript en una activity, lo cual podría provocar vulnerabilidades y nos emplaza a revisar el código. En este caso habría que estudiar detenidamente si es completamente necesario el uso de Javascript. Si es necesarios podemos ignorar este warning aplicando @SuppressLint("SetJavaScriptEnabled") en nuestro código.

Kotlin->Redundant constructs->UnusedSymbol/Unused import directive
Estos avisos nos indican variables o imports en el código que no se usan. Es habitual que al escribir nuestro hagamos pruebas o utilicemos diferentes formas de acceder a nuestras clases, y que al descartar una parte de código no se borren los includes o variables que ya no usamos. Eliminando estas partes de código se solventa el problema.

Proofreading->Typo
Estos warning nos avisan de posibles errores ortográficos en las definiciones de nuestro código. En caso de que sea un error ortográfico simplemente tendremos que corregirlo, pero puede ser que algunos de ellos sean realmente errores sino palabras que no están contempladas en el corrector ortográfico. En estos casos podemos solucionarlo añadiendo la palabra al diccionario a nivel de proyecto o suprimiendo el error.

Kotlin-> Kotlin library and Gradle plugin versions are different
En el archivo gradle nos marca un error entre las versiones del plugin y la librería estándar de kotlin. Sin embargo, tras buscar información acerca del error hemos averiguado que a partir de la versión 1.4.0 de kotlin, la librería estándar se añade automaticamente y no es necesaria la dependencia:
https://kotlinlang.org/docs/reference/whatsnew14.html#dependency-on-the-standard-library-added-by-default


Warnings no solucionados

Androiid->Lint->Performance->Node can be replaced by a TextView with compound drawables

Este warning nos avisa de que podemos hacer una implementación mas eficiente en un layout. En este caso estamos utilizando un LinearLayout con un ImageView y un TextView. Este caso se podría simplificar usando únicamente un TextView, añadiendo la propiedad drawableLeft. Sin embargo, en nuestro caso, la imagen se trata de un gráfico vectorial, cuyo tratamiento es diferente y tras probar varias alternativas no hemos conseguido que funcione correctamente, por lo que simplemente suprimimos el warning con tools:ignore="UseCompoundDrawables".

Kotlin->Redundant constructs->UnusedSymbol

Se ha creado la clase MainApplication para inicializar Koin y se ha añadido al manifiesto, sin embargo, nos sigue marcando qyue la clase no tiene ningún uso. Tras buscar información a cerca del error hemos averiguado que se trata de un bug en Android Studio 
https://issuetracker.google.com/issues/74514347
