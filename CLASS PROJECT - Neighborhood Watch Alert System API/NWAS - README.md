# Neighborhood Watch Alert System API

## Introduction
The Neighborhood Watch Alert System API provides functionalities for managing community members and issuing safety alerts within a neighborhood watch system. It enables communication between community members and facilitates the reporting, tracking, and resolution of safety concerns and incidents.

This API was designed to enhance community safety and engagement through a variety of features. Leveraging data generated from first_names.txt and last_names.txt files, the API dynamically creates community members with unique attributes, ensuring a diverse and evolving dataset every time upon running the application. 

Additionally, the API facilitates the management of safety alerts, including weather advisories, alerts from the local authorities, and community-generated reports about incidents in the neighborhood. With functionalities for CRUD operations, users can seamlessly submit new alerts, update existing ones, remove outdated ones, and they can register, update or unregister members as needed. Every alert has a date, time, listed type (Weather, Authority-reported, community-reported) and a safety tip. 

## Base URL
`http://localhost:5443/api`
For alerts: `http://localhost:5443/api/alerts`
For members: `http://localhost:5443/api/alerts`

## Endpoints

### 1. Manage Community Members

#### View All Community Members

**HTTP Method:** `GET`

**Endpoint:** `/api/members`

**Description:** Retrieve a list of all community members.

**Responses:**
- **200 OK:** List of community members returned successfully.
- Example output:
```json
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 10:03:41 GMT

[
  {
    "name": "Carena",
    "surname": "Emile",
    "age": 48,
    "addressNumber": "5144",
    "phoneNumber": "599-438-3403",
    "hasLivedHereSince": "2016-05-23",
    "id": 1
  },
  {
    "name": "Guglielma",
    "surname": "Puduns",
    "age": 48,
    "addressNumber": "2803",
    "phoneNumber": "956-179-5483",
    "hasLivedHereSince": "2015-05-23",
    "id": 2
  },
  {
    "name": "Salomi",
    "surname": "Dionis",
    "age": 30,
    "addressNumber": "7476",
    "phoneNumber": "770-214-7642",
    "hasLivedHereSince": "2023-05-23",
    "id": 3
  },
  {
    "name": "Clara",
    "surname": "Pen",
    "age": 67,
    "addressNumber": "3052",
    "phoneNumber": "628-841-0019",
    "hasLivedHereSince": "2004-05-23",
    "id": 4
  },
  {
    "name": "Jennette",
    "surname": "Heyde",
    "age": 23,
    "addressNumber": "2554",
    "phoneNumber": "405-595-1142",
    "hasLivedHereSince": "2004-05-23",
    "id": 5
  },
  {
    "name": "Ofelia",
    "surname": "Kurys",
    "age": 26,
    "addressNumber": "4105",
    "phoneNumber": "669-144-4949",
    "hasLivedHereSince": "2008-05-23",
    "id": 6
  },
  {
    "name": "Elfrieda",
    "surname": "Betteann",
    "age": 65,
    "addressNumber": "2671",
    "phoneNumber": "555-926-1633",
    "hasLivedHereSince": "2010-05-23",
    "id": 7
  },
  {
    "name": "Jacquelynn",
    "surname": "Roana",
    "age": 21,
    "addressNumber": "2371",
    "phoneNumber": "588-691-0832",
    "hasLivedHereSince": "2009-05-23",
    "id": 8
  },
  {
    "name": "Judi",
    "surname": "Rolanda",
    "age": 47,
    "addressNumber": "4273",
    "phoneNumber": "635-074-6298",
    "hasLivedHereSince": "2013-05-23",
    "id": 9
  },
  {
    "name": "Sibeal",
    "surname": "Bautista",
    "age": 27,
    "addressNumber": "1272",
    "phoneNumber": "550-293-5047",
    "hasLivedHereSince": "2017-05-23",
    "id": 10
  }
]
Response file saved.
> 2024-05-23T120341.200.json

Response code: 200; Time: 227ms (227 ms); Content length: 1388 bytes (1.39 kB)
```


- **404 Not Found:** No community members found.
- Example output:
```json
GET http://localhost:5443/api/members

HTTP/1.1 404 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 10:54:47 GMT

{
  "timestamp": "2024-05-23T10:54:47.942+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "No community members found",
  "path": "/api/members"
}
Response file saved.
> 2024-05-23T125447.404.json

Response code: 404; Time: 19ms (19 ms); Content length: 139 bytes (139 B)
```
---

#### Add a New Community Member

**HTTP Method:** `POST`

**Endpoint:** `/api/members/add`

**Description:** Register a new community member.

**Request Headers:**
- `Content-Type: application/json`

**Request Body:**
```json
{
  "name": "John",
  "surname": "Doe",
  "age": 30,
  "addressNumber": "123",
  "phoneNumber": "123-456-7890",
  "hasLivedHereSince": "2024-06-15"
}
```

**Responses:**
- **201 Created:** New community member added successfully.
- Example output:
```json
POST http://localhost:5443/api/members/add

HTTP/1.1 201 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 10:55:37 GMT

{
  "name": "John",
  "surname": "Doe",
  "age": 30,
  "addressNumber": "123",
  "phoneNumber": "123-456-7890",
  "hasLivedHereSince": "2024-06-15",
  "id": 11
}
Response file saved.
> 2024-05-23T125537.201.json

Response code: 201; Time: 26ms (26 ms); Content length: 132 bytes (132 B)
```
- **400 Bad Request:** Invalid input.
- Example output:
```json
POST http://localhost:5443/api/members/add

HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 11:00:09 GMT
Connection: close

{
  "timestamp": "2024-05-23T11:00:09.326+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Unable to add community member",
  "path": "/api/members/add"
}
Response file saved.
> 2024-05-23T130009.400.json

Response code: 400; Time: 4ms (4 ms); Content length: 246 bytes (246 B)
```

---

#### Update Contact Information for a Community Member

**HTTP Method:** `PUT`

**Endpoint:** `/api/members/update/{id}`

**Description:** Update contact information for a specific community member by ID.

**Path Parameters:**
- `id` (string): ID of the community member

**Request Headers:**
- `Content-Type: application/json`

**Request Body:**
```json
{
  "phoneNumber": "987-654-3210"
}
```

**Responses:**
- **200 OK:** Community member information updated successfully.
- Example output:
```json
PUT http://localhost:5443/api/members/update/12

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 11:01:16 GMT

{
  "name": "John",
  "surname": "Doe",
  "age": 30,
  "addressNumber": "123",
  "phoneNumber": "987-654-3210",
  "hasLivedHereSince": "2024-06-15",
  "id": 11
}
Response file saved.
> 2024-05-23T130116.200.json

Response code: 200; Time: 12ms (12 ms); Content length: 132 bytes (132 B)
```
- **404 Not Found:** Community member not found with the provided ID.
- Example output:
```json
PUT http://localhost:5443/api/members/update/1

HTTP/1.1 404 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 11:03:37 GMT

{
  "timestamp": "2024-05-23T11:03:37.518+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Unable to update community member with ID: 1",
  "path": "/api/members/update/1"
}
Response file saved.
> 2024-05-23T130337.404.json

Response code: 404; Time: 5ms (5 ms); Content length: 166 bytes (166 B)
```

---

#### Delete a Community Member

**HTTP Method:** `DELETE`

**Endpoint:** `/api/members/delete/{id}`

**Description:** Unregister a community member by ID.

**Path Parameters:**
- `id` (string): ID of the community member

**Responses:**
- **204 OK:** Community member deleted successfully.
- Example output:
```json
DELETE http://localhost:5443/api/members/delete/1

HTTP/1.1 204 
Date: Thu, 23 May 2024 11:39:37 GMT

<Response body is empty>

Response code: 204; Time: 362ms (362 ms); Content length: 0 bytes (0 B)
```
- **404 Not Found:** Community member not found with the provided ID.
- Example output:
```json
DELETE http://localhost:5443/api/members/delete/1

HTTP/1.1 404 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 10:49:25 GMT

{
  "timestamp": "2024-05-23T10:49:25.116+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Unable to delete community member with ID: 1",
  "path": "/api/members/delete/1"
}
Response file saved.
> 2024-05-23T124925.404.json

Response code: 404; Time: 5ms (5 ms); Content length: 166 bytes (166 B)
```
---

### 2. Manage Safety Alerts

#### View All Safety Alerts

**HTTP Method:** `GET`

**Endpoint:** `/api/alerts`

**Description:** Retrieve a list of all safety alerts.

**Responses:**
- **200 OK:** List of safety alerts returned successfully.
- Example output:
```json
GET http://localhost:5443/api/alerts

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 11:07:58 GMT

[
  {
    "alertID": 1,
    "safetyTip": "Take cover immediately, a tornado has been sighted in your area!",
    "alertType": "Weather: Tornado Warning",
    "dateTime": "Tuesday, 2022-06-28, 2:17 AM"
  },
  {
    "alertID": 2,
    "safetyTip": "Be cautious of flooding, heavy rain may lead to flash floods!",
    "alertType": "Weather: Flash Flood Warning",
    "dateTime": "Friday, 2022-05-27, 6:53 PM"
  },
  {
    "alertID": 3,
    "safetyTip": "Prepare for extreme cold and heavy snowfall, a blizzard is forecasted!",
    "alertType": "Weather: Blizzard Warning",
    "dateTime": "Tuesday, 2021-02-02, 10:25 AM"
  },
  {
    "alertID": 4,
    "safetyTip": "Seek shelter indoors, a severe thunderstorm is approaching!",
    "alertType": "Weather: Severe Thunderstorm Warning",
    "dateTime": "Sunday, 2021-08-08, 10:09 PM"
  },
  {
    "alertID": 5,
    "safetyTip": "There is an active earthquake in your area, seek shelter and avoid all operations of any vehicles!",
    "alertType": "Weather: Earthquake Warning",
    "dateTime": "Sunday, 2021-11-28, 3:06 AM"
  },
  {
    "alertID": 6,
    "safetyTip": "Take care to not park illegally in front of a neighbor's driveway.",
    "alertType": "Alert from Authorities: Illegal Parking",
    "dateTime": "Thursday, 2022-12-01, 11:55 AM"
  },
  {
    "alertID": 7,
    "safetyTip": "Stay indoors and report any suspicious activity to the authorities.",
    "alertType": "Alert from Authorities: Ongoing Crime",
    "dateTime": "Thursday, 2020-06-04, 6:43 AM"
  },
  {
    "alertID": 8,
    "safetyTip": "Join your neighborhood watch program for community safety.",
    "alertType": "Alert from Authorities: Neighborhood Event",
    "dateTime": "Thursday, 2023-02-09, 10:00 AM"
  },
  {
    "alertID": 9,
    "safetyTip": "Shots have been fired in your area. Remain indoors and stay safe.",
    "alertType": "Alert from Authorities: Shots Fired",
    "dateTime": "Sunday, 2022-06-19, 3:58 AM"
  },
  {
    "alertID": 10,
    "safetyTip": "A wild animal was seen walking around the area. Keep a safe distance.",
    "alertType": "Alert from Authorities: Dangerous Animal",
    "dateTime": "Sunday, 2023-04-23, 3:03 AM"
  },
  {
    "alertID": 11,
    "safetyTip": "Emergency assistance is required in the area. Reported by: Charlean Schoening, Age: 53, Address: 6147",
    "alertType": "Emergency Assistance",
    "dateTime": "Friday, 2021-01-01, 12:10 PM"
  },
  {
    "alertID": 12,
    "safetyTip": "A dog has been reported missing from the neighborhood. Reported by: Linea Swope, Age: 30, Address: 6987",
    "alertType": "Lost Dog",
    "dateTime": "Friday, 2019-08-23, 1:40 PM"
  },
  {
    "alertID": 13,
    "safetyTip": "A theft was reported by someone in the neighborhood. Reported by: Sapphire Schenck, Age: 23, Address: 1202",
    "alertType": "Property Theft",
    "dateTime": "Friday, 2023-06-09, 1:44 AM"
  },
  {
    "alertID": 14,
    "safetyTip": "A car crash has been reported in the area. Reported by: Ronna Laws, Age: 21, Address: 3951",
    "alertType": "Car Crash",
    "dateTime": "Sunday, 2023-06-04, 4:08 AM"
  },
  {
    "alertID": 15,
    "safetyTip": "A collision between two or more vehicles has been witnessed in the neighborhood. Reported by: Robby Bikales, Age: 32, Address: 7213",
    "alertType": "Car Collision",
    "dateTime": "Monday, 2021-07-19, 5:25 PM"
  },
  {
    "alertID": 16,
    "safetyTip": "A community member has been reported missing. Reported by: Aleda Blondelle, Age: 28, Address: 2517",
    "alertType": "Missing Person",
    "dateTime": "Tuesday, 2021-09-21, 6:32 PM"
  },
  {
    "alertID": 17,
    "safetyTip": "An alert from the neighborhood watch program. Reported by: Vina Derron, Age: 41, Address: 8032",
    "alertType": "Neighborhood Watch Alert",
    "dateTime": "Monday, 2022-05-02, 9:22 AM"
  },
  {
    "alertID": 18,
    "safetyTip": "A car crash has been reported in the area. Reported by: Marlene Sierra, Age: 37, Address: 7523",
    "alertType": "Car Crash",
    "dateTime": "Tuesday, 2022-11-01, 6:03 PM"
  },
  {
    "alertID": 19,
    "safetyTip": "Emergency assistance is required in the area. Reported by: Janeta Geffner, Age: 28, Address: 9008",
    "alertType": "Emergency Assistance",
    "dateTime": "Thursday, 2020-02-13, 8:08 AM"
  },
  {
    "alertID": 20,
    "safetyTip": "A community member has been reported missing. Reported by: Annabell Terryl, Age: 39, Address: 865",
    "alertType": "Missing Person",
    "dateTime": "Monday, 2024-05-20, 3:57 PM"
  }
]
Response file saved.
> 2024-05-23T130758.200.json

Response code: 200; Time: 10ms (10 ms); Content length: 3895 bytes (3.9 kB)
```
- **404 Not Found:** No safety alerts found.
- Example output:
```json
GET http://localhost:5443/api/alerts

HTTP/1.1 404 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 11:11:15 GMT

{
  "timestamp": "2024-05-23T11:11:15.719+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "No alerts found",
  "path": "/api/alerts"
}
Response file saved.
> 2024-05-23T131115.404.json

Response code: 404; Time: 18ms (18 ms); Content length: 127 bytes (127 B)
```
---

#### Add a New Safety Alert

**HTTP Method:** `POST`

**Endpoint:** `/api/alerts/add`

**Description:** Create and issue a new safety alert.

**Request Headers:**
- `Content-Type: application/json`

**Request Body:**
```json
{
  "safetyTip": "Ensure all doors and windows are locked at night.",
  "alertType": "Security"
}
```

**Responses:**
- **201 Created:** New safety alert added successfully.
- Example output:
```json
POST http://localhost:5443/api/alerts/add

HTTP/1.1 201 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 11:17:23 GMT

{
  "alertID": 21,
  "safetyTip": "Ensure all doors and windows are locked at night.",
  "alertType": "Security",
  "dateTime": "Tuesday, 2022-05-17, 11:36 PM"
}
Response file saved.
> 2024-05-23T131723.201.json

Response code: 201; Time: 151ms (151 ms); Content length: 144 bytes (144 B)

```
- **400 Bad Request:** Invalid input.
- Example output:
```json
POST http://localhost:5443/api/alerts/add

HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 11:18:16 GMT
Connection: close

{
  "timestamp": "2024-05-23T11:18:16.858+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Unable to create alert",
  "path": "/api/alerts/add"
}
Response file saved.
> 2024-05-23T131816.400.json

Response code: 400; Time: 19ms (19 ms); Content length: 252 bytes (252 B)
```
---

#### Update Safety Alert Details

**HTTP Method:** `PUT`

**Endpoint:** `/api/alerts/update/{id}`

**Description:** Update details of a specific safety alert by ID.

**Path Parameters:**
- `id` (string): ID of the safety alert

**Request Headers:**
- `Content-Type: application/json`

**Request Body:**
```json
{
  "safetyTip": "Stay away from fallen power lines.",
  "alertType": "Weather, Severe Hurricane Warning"
}
```

**Responses:**
- **200 OK:** Safety alert details updated successfully.
- Example output:
```json
PUT http://localhost:5443/api/alerts/update/2

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 11:21:40 GMT

{
  "alertID": 2,
  "safetyTip": "Stay away from fallen power lines.",
  "alertType": "Weather, Severe Hurricane Warning",
  "dateTime": "Friday, 2023-10-13, 12:31 PM"
}
Response file saved.
> 2024-05-23T132140.200.json

Response code: 200; Time: 35ms (35 ms); Content length: 152 bytes (152 B)
```
- **400 Bad Request:** Invalid input.
- Example output:
```json
PUT http://localhost:5443/api/alerts/update/2

HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 11:22:36 GMT
Connection: close

{
  "timestamp": "2024-05-23T11:22:36.919+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Unable to update alert with ID: 2",
  "path": "/api/alerts/update/2"
}
Response file saved.
> 2024-05-23T132236.400.json
```
Response code: 400; Time: 6ms (6 ms); Content length: 256 bytes (256 B)

- **404 Not Found:** Safety alert not found with the provided ID.
- Example output:
```json
PUT http://localhost:5443/api/alerts/update/200

HTTP/1.1 404 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 11:24:18 GMT

{
  "timestamp": "2024-05-23T11:24:18.819+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Unable to update alert with ID: 200",
  "path": "/api/alerts/update/200"
}
Response file saved.
> 2024-05-23T132418.404.json

Response code: 404; Time: 6ms (6 ms); Content length: 158 bytes (158 B)
```
---

#### Delete a Safety Alert

**HTTP Method:** `DELETE`

**Endpoint:** `/api/alerts/delete/{id}`

**Description:** Remove a safety alert by ID.

**Path Parameters:**
- `id` (string): ID of the safety alert

**Responses:**
- **204 OK:** Safety alert deleted successfully.
- Example output:
```json
DELETE http://localhost:5443/api/alerts/1

HTTP/1.1 204 
Date: Thu, 23 May 2024 11:33:08 GMT

<Response body is empty>

Response code: 204; Time: 368ms (368 ms); Content length: 0 bytes (0 B)
```
- **404 Not Found:** Safety alert not found with the provided ID.
- Example output:
```json
DELETE http://localhost:5443/api/alerts/100

HTTP/1.1 404 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 23 May 2024 11:41:24 GMT

{
  "timestamp": "2024-05-23T11:41:24.145+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Unable to delete alert with ID: 100",
  "path": "/api/alerts/100"
}
Response file saved.
> 2024-05-23T134124.404.json

Response code: 404; Time: 395ms (395 ms); Content length: 151 bytes (151 B)
```
---

These endpoints (and more) collectively form the Neighborhood Watch Alert System API, providing comprehensive functionalities for managing community members and safety alerts within a neighborhood watch system.
