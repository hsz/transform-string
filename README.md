# transform-string

The `transform-string` library providing a set of functions for string case transformation in an intuitive manner.

## Installation

```
dependencies {
    implementation("ski.chrzanow:transform-string:0.1.0")
}
```

## Usage example

```kotlin
"hello world".transform_string()    // hello_world
"hello world".TRANSFORMSTRING()     // HELLOWORLD
"hello world".TransformString()     // HelloWorld
"hello world".`TRANSFORM-STRING`()  // HELLO-WORLD
"hello world".tRaNsFoRmStRiNg()  // hElLo WoRlD
```

If you find that concept interesting and have more ideas for it — do not hesitate to create a Pull Request!

- [Jakub](https://twitter.com/hszanowski)
- 
