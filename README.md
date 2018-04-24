# Android Architecture Sample Application
安卓架构组件示例工程，使用完整的安卓架构组件，包含 ViewModel，LiveData 以及 Room。<br/>
工程包含 Kotlin 版本和 Java 版本。<br/>
工程模拟加载版本号请求，UI 层通过仓库绑定版本模型的 LiveData，业务层请求版本内容后，直接更新入数据库，数据库再依靠 LiveData 通知UI
