# Bai tap Java

Project nay gom 4 bai tap Java:

- `Bai1HashMapDemo`: lam viec voi `HashMap<Integer, String>`.
- `Bai2AdapterPatternDemo`: minh hoa Adapter Pattern voi `MediaPlayer` va `VlcPlayer`.
- `Bai3ThreadDemo`: chay `Thread` va `Runnable`.
- `Bai4SumCalculatorApp`: ung dung JavaFX cong hai so bang `GridPane`.

## Cach bien dich

Bien dich cac bai console:

```bash
javac -encoding UTF-8 src/Bai1HashMapDemo.java src/Bai2AdapterPatternDemo.java src/Bai3ThreadDemo.java
```

## Cach chay

```bash
java -cp src Bai1HashMapDemo
java -cp src Bai2AdapterPatternDemo
java -Dfile.encoding=UTF-8 -cp src Bai3ThreadDemo
```

## Bai 4 JavaFX

May hien tai da co JavaFX 17 tai:

```bash
/Users/dovandat/Downloads/javafx-sdk-17.0.18/lib
```

Bien dich:

```bash
javac --module-path /Users/dovandat/Downloads/javafx-sdk-17.0.18/lib --add-modules javafx.controls src/Bai4SumCalculatorApp.java
```

Chay:

```bash
java --module-path /Users/dovandat/Downloads/javafx-sdk-17.0.18/lib --add-modules javafx.controls -cp src Bai4SumCalculatorApp
```

## Chay bang VS Code

Project da co san cau hinh trong thu muc `.vscode/` cho Bai 4.

1. Mo thu muc `/Users/dovandat/bailuyentap10` bang VS Code.
2. Cai extension `Extension Pack for Java` neu VS Code chua co.
3. Mo file `src/Bai4SumCalculatorApp.java`.
4. Chon `Run and Debug` va chay cau hinh `Run Bai4SumCalculatorApp`.

Neu VS Code hoi ve JDK, hay chon JDK 17.
