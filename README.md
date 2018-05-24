# Curso de Aplicaciones Android con Java (Básico - Intermedio) - Academia Móviles

## Lesson4 - Wednesday, May 23, 2018

- Review

- Lesson

- Samples

- Homework

- Resources

## Review

### ¿Qué temas vimos en la clase pasada ?

- Layouts : LinearLayout , RelativeLayout

- Android Resources

- Android Manifest

### Activities

- Completemos los ejemplos de la clase 3 , UISamples https://github.com/emedinaa/amoviles-android-basic-intermediate/tree/Lesson3/UISamples

## Lesson

- Implementar la navegación en una aplicación Android

- Interfaz de usuario en Android: Layouts

- Controles UI básicos

- Controles de selección

- Implementar aplicaciones para múltiples tamaños de pantalla 

- Utilizando ActionBar en las aplicaciones

- Creación de menús y submenús

- Notificaciones (Toast, barra de estados, Diálogos, Snackbar)

- Ciclo de vida de las actividades

- Uso de la cámara en las aplicaciones

### User Events

  - Widgets
  - User Events

### UI components

- Buttons
- Checkboxes
- Radio buttons
- Campos de textos y labels
- Spinners
- Pickers

Material design for Android https://developer.android.com/guide/topics/ui/look-and-feel/


### Eventos

```java
 btnSignUp =(Button)findViewById(R.id.btnSignUp);
 
 ...
 
   btnSignUp.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
            //action
        }
  });
```

```java
  rbGenero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                  switch (checkedId) {
                      case R.id.rbM:
                          genero = 1;
                          break;
                      case R.id.rbF:
                          genero = 2;
                          break;
                  }
              }
  });
```

```java

  spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                  Log.v("CONSOLE", "spLocation" + adapterView.getAdapter().getItem(i));
                  localidad = adapterView.getAdapter().getItem(i).toString();
              }

              @Override
              public void onNothingSelected(AdapterView<?> adapterView) {

              }
          });
  }
```
## Samples

- UISamples

- UIEvents
<img src="https://github.com/learning-android-pe/training-resources/blob/master/ui/ui-events.png" height="360" /> 

<img src="https://github.com/learning-android-pe/training-resources/blob/master/ui/ui-events1.png" height="360" /> 


- DialogSamples
<img src="https://github.com/learning-android-pe/training-resources/blob/master/ui/dialogs-1.png" height="360" /> <img src="https://github.com/learning-android-pe/training-resources/blob/master/ui/dialogs-2.png" height="360" /> 

- NavigationSamples
<img src="https://github.com/learning-android-pe/training-resources/blob/master/ui/navigation-1.png" height="360" /> <img src="https://github.com/learning-android-pe/training-resources/blob/master/ui/navigation-2.png" height="360" />

## Homework

- Realizar los ejercicios 02,03,04  utilizando solo ConstraintLayout https://github.com/emedinaa/amoviles-android-basic-intermediate/tree/Lesson3/exercises

## Resources

- User Events https://developer.android.com/guide/topics/ui/ui-events.html

- Controles de entrada https://developer.android.com/guide/topics/ui/controls.html

- Android Design https://developer.android.com/design/

- Material Design for Android https://developer.android.com/guide/topics/ui/look-and-feel/


