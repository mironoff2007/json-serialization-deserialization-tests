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
![image](https://user-images.githubusercontent.com/18057056/190706053-388c0293-2df9-4d8c-9cd7-52d3769b72e7.png)

![image](https://user-images.githubusercontent.com/18057056/190706116-abafa661-b02a-43cd-b06c-3f0e8d6d5052.png)

![image](https://user-images.githubusercontent.com/18057056/190706158-ca5a8bd9-18ea-4c5b-8771-cbd4b0bc6b3e.png)

GeoJson test

![image](https://user-images.githubusercontent.com/18057056/190707233-b51ed6c4-28e3-4d4e-9359-037d0e888f4c.png)

![image](https://user-images.githubusercontent.com/18057056/190706308-e1d72940-090e-4227-902c-1fe78bd9a059.png)

![image](https://user-images.githubusercontent.com/18057056/190706714-9d0aa3b8-a5f7-423c-83d4-cab40d8d1886.png)
