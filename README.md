# Selenium Essentials Suite

A professional, well-documented learning repository demonstrating reliable Selenium UI tests, BDD with Cucumber, and lightweight API checks with Rest Assured.

---

Table of contents

- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Quick start](#quick-start)
- [Run examples](#run-examples)
- [Reports](#reports)
- [Project structure](#project-structure)
- [Key files](#key-files)
- [Stability and design choices](#stability-and-design-choices)
- [CI example (GitHub Actions)](#ci-example-github-actions)
- [Author](#author)

## Overview

Small, focused tests to teach practical automation patterns:

- Page Object Model with reusable helpers
- JUnit 5 test cases and parameterized examples
- Cucumber scenarios running on the JUnit Platform
- Rest Assured API smoke checks

All tests run headless-friendly and are tuned for local laptops and CI runners.

## Features

- Fast, deterministic web form tests via a local HTML fixture
- Robust Chrome options and headless flags wired via an env var
- Automatic screenshots on failures in Cucumber
- Safe interactions to reduce click-intercept flakiness
- Clean project structure and easy-to-run commands

## Prerequisites

- Java 17 (JDK 17)
- Maven 3.8+
- Chrome or Chromium installed (or a compatible chromedriver binary)

Verify tools

```bash
java -version
mvn -v
```

Optional: provide a chromedriver manually

```bash
export WEBDRIVER_CHROME_DRIVER=/path/to/chromedriver
```

## Quick start

Run the full suite (recommended headless):

```bash
HEADLESS=1 mvn -q test
```

Run only the Cucumber suite:

```bash
HEADLESS=1 mvn -q -Dtest=CucumberTestSuite test
```

Run a single JUnit class:

```bash
mvn -q -Dtest=com.mytham.essentials.tests.WebFormPOMTest test
```

Run a single test method:

```bash
mvn -Dtest=com.mytham.essentials.tests.WebFormPOMTest#formHappyPaths test
```

## Run examples

- Full suite: `HEADLESS=1 mvn -q test`
- Cucumber only: `HEADLESS=1 mvn -q -Dtest=CucumberTestSuite test`
- Single class: `mvn -q -Dtest=com.mytham.essentials.tests.IFrameTest test`

## Reports

Outputs are written under `target/`:

- Cucumber HTML: `target/cucumber-report.html`
- Cucumber JSON: `target/cucumber.json`
- Screenshots on failure: `target/screenshots/`

## Project structure

```text
src/
 └─ test/
    ├─ java/
    │  └─ com/mytham/essentials/
    │     ├─ pages/        # Page Objects
    │     ├─ steps/        # Cucumber steps
    │     ├─ support/      # DriverManager, hooks, base
    │     └─ tests/        # JUnit test classes
    └─ resources/
       ├─ features/        # Cucumber feature files
       └─ web/             # Local web fixtures
```

## Key files

- Project configuration: [pom.xml](pom.xml)
- Cucumber suite entry: [CucumberTestSuite.java](src/test/java/com/mytham/essentials/tests/CucumberTestSuite.java)
- Driver lifecycle and options: [DriverManager.java](src/test/java/com/mytham/essentials/support/DriverManager.java)
- Cucumber hooks (screenshots, cleanup): [CucumberHooks.java](src/test/java/com/mytham/essentials/support/CucumberHooks.java)
- Page Object for the demo form: [WebFormPage.java](src/test/java/com/mytham/essentials/pages/WebFormPage.java)
- Local HTML fixture for the form: [web-form.html](src/test/resources/web/web-form.html)
- Feature files: [features/](src/test/resources/features/)

## Stability and design choices

- Local HTML fixture removes external dependency and makes form tests deterministic.
- Chrome options tuned for CI; `HEADLESS=1` enables headless and resource flags automatically.
- `safeClick()` helper falls back to Actions/JS to avoid click interception.
- Cucumber `@After` always quits the driver and attaches a screenshot on failure.

## CI example (GitHub Actions)

Create `.github/workflows/maven.yml`:

```yaml
name: CI
on: [push, pull_request]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Setup JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
      - name: Install Chromium
        run: |
          sudo apt-get update
          sudo apt-get install -y chromium-browser
      - name: Run tests
        env:
          HEADLESS: 1
        run: mvn -q test
      - name: Upload artifacts
        uses: actions/upload-artifact@v4
        with:
          name: test-artifacts
          path: |
            target/cucumber-report.html
            target/screenshots/
```

## Author

Maintainer: Mytham Jasim

If you find issues or want enhancements, open an issue or a PR. For structural questions about the suite, see:

- Project file: [pom.xml](pom.xml)
- Suite entry: [CucumberTestSuite.java](src/test/java/com/mytham/essentials/tests/CucumberTestSuite.java)
- Driver lifecycle: [DriverManager.java](src/test/java/com/mytham/essentials/support/DriverManager.java)
- Hooks: [CucumberHooks.java](src/test/java/com/mytham/essentials/support/CucumberHooks.java)
- Page Object: [WebFormPage.java](src/test/java/com/mytham/essentials/pages/WebFormPage.java)
