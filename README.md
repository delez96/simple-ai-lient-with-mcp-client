# Java Spring AI Demo

Этот проект демонстрирует интеграцию Spring Boot с AI-сервисами (например, через chatClient).

## Структура проекта
- `src/main/java/org/example/` — основной Java-код (контроллеры, сервисы)
- `src/main/resources/` — конфигурационные файлы (`application.yml`, `mcp-servers-config.json`)
- `pom.xml` — зависимости Maven

## Запуск проекта
1. Убедитесь, что установлен JDK 17+ и Maven.
2. Проверьте настройки в `src/main/resources/application.yml` (например, параметры подключения к AI-сервису).
3. Соберите и запустите проект:
   ```sh
   mvn spring-boot:run
   ```