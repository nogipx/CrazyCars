package dev.nogipx.java.crazycars

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class ExtraInfo(val title: String)