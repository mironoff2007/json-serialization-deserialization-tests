# json-serialization-deserialization-tests

Comparison of json deserialization:
  <br />-gson
  <br />-gson without annotations
  <br />-jackson 
  <br />-kotlinx
  <br />-moshi 
  
  1) Deserialization of list with 50 objects-tree of 200 inner classes. 
   *moshi can't deserialize more deep nested object than about 200, list of such objects is added to increase parse time, parse time of 1 object is too low)
  3) Deserialization of big geo json with coorinates of polylines.
  Json string for 2 cases is located in .json file approx. 1.3 Mb in raw resources.

  Test devices
  1) Asus ZC500TG (Asus)
  2) Realme c25s (Realm)
  3) Emulator Pixel 4 api 29 (emu) (notebook i7-4600u, 16 gb ram)

Nested object test 

![image](https://user-images.githubusercontent.com/18057056/190845720-da8a6b94-213b-4ce8-8512-af884018df19.png)

![image](https://user-images.githubusercontent.com/18057056/190845738-d1fd8736-93bf-48d6-ae5d-cd0bdad4bc6f.png)

![image](https://user-images.githubusercontent.com/18057056/190845796-ace2196c-3fea-4776-95d0-956f6eae5f45.png)

GeoJson test

![image](https://user-images.githubusercontent.com/18057056/190845823-96def309-1a52-45e8-a74d-b976520eee8c.png)

![image](https://user-images.githubusercontent.com/18057056/190845836-3965a5f5-d5d7-4fb8-a10c-23e0a47092fc.png)

![image](https://user-images.githubusercontent.com/18057056/190845849-9e00c145-ef7a-4ebd-aefd-21e2f7e5d268.png)
