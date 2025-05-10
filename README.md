### Архитектура
### Приложение разделено на 5 микросервисов:
1. user-service - обрабатывает запросы пользователей
2. event-service - обрабатывает запросы событий
3. request-service обрабатывает заявками пользователей на участие в событиях
4. comment-service - обрабатывает комментарии пользователей
5. interaction-api - связывает все модули с помощью DTO, ENUM, Exception и Feign-клиентов

Все микросервисы при запуске регистрируются в discovery-server.
Все конфигурации загружаются d config-server.
Все запросы идут через gateway-server.

### Описание внутреннего API для взаимодействия сервисов:
#### User-service
1. Получение списка пользователей: GET/admin/users
2. Поиск пользователя по id: GET/admin/users/{userId}
3. Проверка пользователя на существование: GET/admin/{userId}

#### Event-service:
1. Поиск события по id: GET/events/{id}
2. Добавление события: POST/users/{userId}/events
3. Обновление заявок на участие: PATCH/admin/events/{eventId}
4. Проверка на существование события: GET/admin/events/{id}

#### Request-service:
1. Получение всех заявок: GET/users/{userId}/requests
2. Добавление заявки: POST/users/{userId}/requests
3. Отклонение заявки: PATCH/users/{userId}/requests/{requestId}/cancel
4. Поиск всех заявок по id события: GET/requests/event/{eventId}
5. Поиск подтвержденных заявок по id события: GET/requests/event/confirmed/{eventId}
6. Обновление статуса заявки: PATCH/requests/status/{id}

#### Comment-service
1. Получение всех комментариев по id события: GET/comments/{eventId}
2. Создание комментария: POST/users/{userId}/comments
3. Обновление комментария: PATCH/users/{userId}/comments/{commentId}
4. Удаление комментария: DELETE/users/{userId}/comments/{commentId}

#### Stats-server
1. Получение статистики: GET/stats
2. Сохранение статистики: POST/hit

### Ссылки на внешний API: 
1. https://raw.githubusercontent.com/yandex-praktikum/java-explore-with-me/main/ewm-stats-service-spec.json
2. https://raw.githubusercontent.com/yandex-praktikum/java-explore-with-me/main/ewm-main-service-spec.json