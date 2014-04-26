int sensorPin1 = A0;
int sensorPin2 = A1;
int value1 = 0;
int value2 = 0;

//value 1 es el eje vertical
//value 2 es el eje horizontal
void setup() {
Serial.begin(9600);
}

void loop() {
value1 = analogRead(sensorPin1);
value2 = analogRead(sensorPin2);
verDatos();
}
void verDatos(){
  value1 = map(value1,0,1023,49,180);
  //en realidad seria mapear todas las posiciones map(value1,0,1023,limite_borde_superior,limite_borde_inferior);
  Serial.print(value1);
  Serial.print(" ");
  value2 = map(value2,0,1023,201,65);
  //en realidad seria mapear todas las posiciones map(value1,0,1023,limite_borde_der,limite_borde_izq);
  Serial.println(value2);
  delay(50);
}
