# Quarkus Gradle Multi-Project Extension Example

This project demonstrates how to build Quarkus extensions using Gradle with a multi-module structure. It includes:

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
│   └── build.gradle
├── extensions/
│   ├── example-extension/         # First Quarkus extension
│   │   ├── deployment/           # Build-time module
│   │   ├── runtime/              # Runtime module
│   │   └── settings.gradle
│   └── another-example-extension/ # Second extension (depends on first)
│       ├── deployment/
│       ├── runtime/
│       └── settings.gradle
├── libraries/                     # Shared libraries
│   ├── libraryA/
│   ├── libraryB/
│   └── settings.gradle
├── gradle.properties              # Common properties
└── settings.gradle                # Root settings with composite builds
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

1. **Multi-Module Gradle Structure**: Uses Gradle composite builds to manage multiple related projects
2. **Quarkus Extension Plugin**: Properly configured with `io.quarkus.extension` plugin
3. **Extension Dependencies**: Shows how one extension can depend on another
4. **Library Integration**: Extensions can use external libraries
5. **Configuration**: Demonstrates `@ConfigRoot` configuration (though using legacy style)
6. **Testing**:
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

## Next Steps

To convert this project to Kotlin DSL:
1. Rename all `*.gradle` files to `*.gradle.kts`
2. Convert the Groovy syntax to Kotlin
3. Update plugin and dependency declarations

## License

This example is provided as-is for educational purposes.