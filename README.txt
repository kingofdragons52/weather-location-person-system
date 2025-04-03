

## Сервисы
- **Person-Service** (порт 8080) - информация о людях
- **Location-Service** (порт 8081) - данные о городах и координатах
- **Weather-Service** (порт 8082) - данные о погоде

## Заполнение тестовых данных

### 1. Добавление городов (Location-Service)

POST http://localhost:8081/locations
Content-Type: application/json

[
    {
        "city": "Moscow",
        "latitude": 55.75,
        "longitude": 37.61
    },
    {
        "city": "Saint Petersburg",
        "latitude": 59.93,
        "longitude": 30.31
    },
    {
        "city": "Novosibirsk",
        "latitude": 55.01,
        "longitude": 82.56
    },
    {
        "city": "Sevastopol",
        "latitude": 44.35,
        "longitude": 33.31
    },
    {
        "city": "Winterfell",
        "latitude": 69.09,
        "longitude": 35.08
    }
]
2. Добавление погоды (Weather-Service)

POST http://localhost:8082/weather
Content-Type: application/json

[
    {
        "latitude": 55.75,
        "longitude": 37.61,
        "city": "Moscow",
        "description": "Ясно",
        "temperature": 25.0
    },
    {
        "latitude": 59.93,
        "longitude": 30.31,
        "city": "Saint Petersburg",
        "description": "Облачно",
        "temperature": 18.0
    },
    {
        "latitude": 55.01,
        "longitude": 82.56,
        "city": "Novosibirsk",
        "description": "Дождь",
        "temperature": 12.0
    },
    {
        "latitude": 44.35,
        "longitude": 33.31,
        "city": "Sevastopol",
        "description": "Солнечно",
        "temperature": 28.0
    },
    {
        "latitude": 69.09,
        "longitude": 35.08,
        "city": "Winterfell",
        "description": "Снег",
        "temperature": -15.0
    }
]
3. Добавление людей (Person-Service)

POST http://localhost:8080/person
Content-Type: application/json

[
    {
        "id": 1,
        "name": "Ivan",
        "location": "Moscow"
    },
    {
        "id": 2,
        "name": "Anna",
        "location": "Saint Petersburg"
    },
    {
        "id": 3,
        "name": "Dmitry",
        "location": "Novosibirsk"
    },
    {
        "id": 4,
        "name": "Alex",
        "location": "Sevastopol"
    },
    {
        "id": 5,
        "name": "Jon Snow",
        "location": "Winterfell"
    }
]


Примеры запросов
Получение погоды по координатам (с названием города)

# Москва
GET http://localhost:8082/weather/coordinates?latitude=55.75&longitude=37.61

# Винтерфелл
GET http://localhost:8082/weather/coordinates?latitude=69.09&longitude=35.08
Пример ответа:

{
    "id": 5,
    "latitude": 69.09,
    "longitude": 35.08,
    "city": "Winterfell",
    "description": "Снег",
    "temperature": -15.0
}


Получение погоды для человека

# Для Алекса (Севастополь)
GET http://localhost:8080/person/4/weather

# Для Джона Сноу (Винтерфелл)
GET http://localhost:8080/person/5/weather
Пример ответа:

json
Copy
{
    "personId": 5,
    "personName": "Jon Snow",
    "city": "Winterfell",
    "weatherDescription": "Снег",
    "temperature": -15.0
}