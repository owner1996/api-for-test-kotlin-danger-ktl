@file:DependsOn("io.github.ackeecz:danger-kotlin-detekt:0.1.4")

import io.github.ackeecz.danger.detekt.DetektPlugin

import systems.danger.kotlin.*

import java.nio.file.Files
import java.nio.file.Paths
import java.util.function.BiPredicate
import java.util.stream.Collectors

register plugin DetektPlugin

danger(args) {

    onGitHub {
        val isTrivial = pullRequest.title.contains("#trivial")

        // Big PR Check
        if ((pullRequest.additions ?: 0) - (pullRequest.deletions ?: 0) > 300) {
            warn("Big PR, try to keep changes smaller if you can")
        }

        // Work in progress check
        if (pullRequest.title.contains("WIP", false)) {
            warn("PR is classed as Work in Progress")
        }
    }

    val detektReports = Files.find(Paths.get(""), 10, BiPredicate { path, _ ->
        val fileName = path.toFile().name
        fileName.endsWith("detekt.xml")
    }).map { it.toFile() }.collect(Collectors.toList())

    DetektPlugin.parseAndReport(*detektReports.toTypedArray())
}