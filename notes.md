# 笔记

## dependencies
- implementation，对于使用了该部分的编译有效，当前项目有效，其他项目如依赖当前项目，其他项目访问使用时无效，即对当前有效，对外部无效。
- api，相比implementation，该方式不进行隔离。
- testCompile，单元测试代码和打包测试时有效。
- debugCompile，debug模式编译和debug打包时有效。
- releaseCompile，release模式编译和打包时有效。