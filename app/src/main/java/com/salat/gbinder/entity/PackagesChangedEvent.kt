package com.salat.gbinder.entity

sealed class PackagesChangedEvent(val packageName: String) {
    class Added(val pkg: String) : PackagesChangedEvent(pkg)
    class Removed(val pkg: String) : PackagesChangedEvent(pkg)
    class Changed(val pkg: String) : PackagesChangedEvent(pkg)
}
