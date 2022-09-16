# json-serialization-deserialization-tests

Comparison of json deserialization:
  <br />-gson
  <br />-gson without annotations
  <br />-jackson 
  <br />-kotlinx
  <br />-moshi 
  
  1) Deserialization of list with 50 objects-tree of 200 inner classes.
  2) Deserialization of big geo json with coorinates of polylines.
  Json string for 2 cases is located in .json file approx. 1.3 Mb in raw resources.

  Test devices
  1) Asus ZC500TG (Asus)
  2) Realme c25s (Realm)
  3) Emulator Pixel 4 api 29 (emu) (notebook i7-4600u, 16 gb ram)



