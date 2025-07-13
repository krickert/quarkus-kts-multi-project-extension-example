# Quarkus Gradle Kotlin DSL Multi-Project Extension Example

This project demonstrates how to build Quarkus extensions using Gradle with Kotlin DSL in a multi-module structure. It includes:

- Two Quarkus extensions (`example-extension` and `another-example-extension`)
- Two shared libraries (`libraryA` and `libraryB`)
- A sample application that uses the extensions
- Comprehensive test coverage (unit tests, integration tests, and deployment tests)

This example is based on the official Quarkus test resources but configured as a standalone project that can be used as a template.

## Project Structure

```
.
├── application/                    # Sample application using the extensions
│   ├── src/
│   │   ├── main/                  # Application source code
│   │   ├── test/                  # Unit tests
│   │   └── integrationTest/       # Integration tests
│   └── build.gradle.kts
├── extensions/
│   ├── example-extension/         # First Quarkus extension
│   │   ├── deployment/           # Build-time module
│   │   ├── runtime/              # Runtime module
│   │   └── settings.gradle.kts
│   └── another-example-extension/ # Second extension (depends on first)
│       ├── deployment/
│       ├── runtime/
│       └── settings.gradle.kts
├── libraries/                     # Shared libraries
│   ├── libraryA/
│   ├── libraryB/
│   └── settings.gradle.kts
├── gradle.properties              # Common properties
└── settings.gradle.kts            # Root settings with composite builds
```

## Prerequisites

- JDK 17 or later
- Gradle 8.14.2 (included via wrapper)

## Building the Project

```bash
# Build everything
./gradlew build

# Build just the application
./gradlew :application:build
```

## Running Tests

```bash
# Run unit tests
./gradlew test

# Run application unit tests only
./gradlew :application:test

# Run application integration tests
./gradlew :application:quarkusIntTest

# Run deployment module tests (extension tests)
./gradlew :example-extension:example-extension-deployment:test
```

## Running the Application

```bash
# Run in development mode
cd application && ../gradlew quarkusDev

# The application will be available at http://localhost:8080
# The hello endpoint: http://localhost:8080/hello
```

## Key Features Demonstrated

1. **Kotlin DSL**: All Gradle build files use Kotlin DSL (`.gradle.kts`) for type-safe build configuration
2. **Multi-Module Gradle Structure**: Uses Gradle composite builds to manage multiple related projects
3. **Quarkus Extension Plugin**: Properly configured with `io.quarkus.extension` plugin
4. **Extension Dependencies**: Shows how one extension can depend on another
5. **Library Integration**: Extensions can use external libraries
6. **Configuration**: Demonstrates `@ConfigRoot` configuration (though using legacy style)
7. **Testing**:
   - Unit tests in applications
   - Integration tests using `@QuarkusIntegrationTest`
   - Deployment tests using `QuarkusUnitTest`

## Extension Details

### example-extension
- Provides configuration via `QuarkusExampleExtensionConfig`
- Registers `LibA` as a CDI bean
- Adds the "example" feature to Quarkus

### another-example-extension
- Depends on `example-extension`
- Uses `libraryB`
- Demonstrates extension composition

## Configuration

The application can be configured via `application.properties`:

```properties
# Enable/disable example extension functionality
quarkus.example.extension.enabled=false

# This property exists but isn't used by the application
quarkus.anotherExample.extension.enabled=false
```

## Known Issues

- The configuration uses the legacy `@ConfigRoot` style and should be migrated to `@ConfigMapping`
- The warning about unrecognized configuration key for `anotherExample.extension` is expected as it's not used in the application code

## Kotlin DSL Specifics

This project uses Gradle Kotlin DSL throughout. Key syntax differences from Groovy:
- String literals use double quotes
- Plugin IDs use the `id()` function
- Dependencies use function call syntax: `implementation()`, `api()`, etc.
- Property access uses Kotlin delegation: `val propertyName: String by project`
- Plugin configuration blocks use property setters: `deploymentModule.set()`
- Task configuration uses `tasks.taskName { }` syntax

## License

This example is provided as-is for educational purposes.