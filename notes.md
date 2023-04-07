# 笔记

## dependencies
- implementation，对于使用了该部分的编译有效，当前项目有效，其他项目如依赖当前项目，其他项目访问使用时无效，即对当前有效，对外部无效。
- api，相比implementation，该方式不进行隔离。
- testCompile，单元测试代码和打包测试时有效。
- debugCompile，debug模式编译和debug打包时有效。
- releaseCompile，release模式编译和打包时有效。

[官方](https://docs.gradle.org/current/userguide/java_plugin.html)
- implementation  
Implementation only dependencies.
- compileOnly  
Compile time only dependencies, not used at runtime.
- compileClasspath extends compileOnly, implementation  
Compile classpath, used when compiling source. Used by task compileJava.
- annotationProcessor  
Annotation processors used during compilation.
- runtimeOnly  
Runtime only dependencies.
- runtimeClasspath extends runtimeOnly, implementation  
Runtime classpath contains elements of the implementation, as well as runtime only elements.
- testImplementation extends implementation  
Implementation only dependencies for tests.
- testCompileOnly  
Additional dependencies only for compiling tests, not used at runtime.
- testCompileClasspath extends testCompileOnly, testImplementation  
Test compile classpath, used when compiling test sources. Used by task compileTestJava.
- testRuntimeOnly extends runtimeOnly  
Runtime only dependencies for running tests.
- testRuntimeClasspath extends testRuntimeOnly, testImplementation  
Runtime classpath for running tests. Used by task test.
- archives  
Artifacts (e.g. jars) produced by this project. Used by Gradle to determine "default" tasks to execute when building.
- default extends runtimeElements  
The default configuration used by a project dependency on this project. Contains the artifacts and dependencies required by this project at runtime.
