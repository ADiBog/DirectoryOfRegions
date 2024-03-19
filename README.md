### 1. ��������� ���� ��������

- **Method**: GET
- **URL**: http://localhost:8080/regions
- **Body**: No body required

### 2. ��������� ������� �� ID

- **Method**: GET
- **URL**: http://localhost:8080/regions/{id}
    - �������� {id} �� ���������� ID �������, ������� ������ ��������.
- **Body**: No body required

### 3. �������� ������ �������

- **Method**: POST
- **URL**: http://localhost:8080/regions
- **Headers**: Content-Type: application/json
- **Body**:
  {
  "name": "�������� �������",
  "code": "��� �������"
  }

    - �������� "�������� �������" � "��� �������" �� ��������������� ��������.

### 4. ���������� �������

- **Method**: PUT
- **URL**: http://localhost:8080/regions/{id}
    - �������� {id} �� ID �������, ������� ������ ��������.
- **Headers**: Content-Type: application/json
- **Body**:
  {
  "name": "����� �������� �������",
  "code": "����� ��� �������"
  }

    - �������� "����� �������� �������" � "����� ��� �������" �� ����� ��������.

### 5. �������� �������

- **Method**: DELETE
- **URL**: http://localhost:8080/regions/{id}
    - �������� {id} �� ID �������, ������� ������ �������.
- **Body**: No body required