package ru.jalolov.services.apifortestkotlindangerktl

//import ru.jalolov.services.apifortestkotlindangerktl.plugin.DetektPlugin
import io.github.ackeecz.danger.detekt.DetektPlugin
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.function.BiPredicate
import java.util.stream.Collectors

@SpringBootApplication
class ApiForTestKotlinDangerKtlApplication

fun main(args: Array<String>) {
	val detektReports = Files.find(Paths.get(""), 10, BiPredicate { path, _ ->
		val fileName = path.toFile().name
		fileName.endsWith("detekt.xml")
	}).map { it.toFile() }.collect(Collectors.toList())

	DetektPlugin.parseAndReport(*detektReports.toTypedArray())

	runApplication<ApiForTestKotlinDangerKtlApplication>(*args)
}