### 1. Получение всех регионов

- **Method**: GET
- **URL**: http://localhost:8080/regions
- **Body**: No body required

### 2. Получение региона по ID

- **Method**: GET
- **URL**: http://localhost:8080/regions/{id}
  - Замените {id} на конкретный ID региона, который хотите получить.
- **Body**: No body required

### 3. Создание нового региона

- **Method**: POST
- **URL**: http://localhost:8080/regions
- **Headers**: Content-Type: application/json
- **Body**:
  {
  "name": "Название региона",
  "code": "Код региона"
  }

  - Замените "Название региона" и "Код региона" на соответствующие значения.

### 4. Обновление региона

- **Method**: PUT
- **URL**: http://localhost:8080/regions/{id}
  - Замените {id} на ID региона, который хотите обновить.
- **Headers**: Content-Type: application/json
- **Body**:
  {
  "name": "Новое название региона",
  "code": "Новый код региона"
  }

  - Замените "Новое название региона" и "Новый код региона" на новые значения.

### 5. Удаление региона

- **Method**: DELETE
- **URL**: http://localhost:8080/regions/{id}
  - Замените {id} на ID региона, который хотите удалить.
- **Body**: No body required