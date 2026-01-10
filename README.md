# 基于Android 的宠物社区App
该项目为大二课设

## 选题背景
本选题拟开发一款基于Android平台的猫咪记录应用——“GPNU猫咪记录”，旨在通过手机应用帮助猫主更好地记录猫咪的各类信息，包括饮食、体重、活动、健康状况等。同时，该应用将通过数据分析为用户提供个性化的养宠建议，提升宠物管理的智能化水平。通过该应用，猫主可以方便地查看猫咪的成长轨迹，合理安排宠物的饮食和健康管理，提升宠物的生活质量。
随着大数据、人工智能技术的进步，智能设备与手机应用的结合为宠物管理带来了全新的可能性。开发“GPNU猫咪记录”不仅满足市场需求，也为宠物行业的数字化转型提供了创新的解决方案。因此，本选题具有较高的市场潜力和研究价值。
因此，开发一个适合手机用户的猫咪记录应用是非常有意义和有价值的。一个好的猫咪记录应该具备以下几个方面的功能和特点：
1. 用户注册、登录等基本功能，保障用户的安全和隐私。
2. 用户发布、浏览、收藏、删除等记录操作功能，满足用户的社交需求。
3. 用户可以创建并管理自己宠物的档案，记录宠物的基本信息和当天的心情记录
4. 用户的所在地区查询，方便找到猫咪所在地。
5. 用户点进APP具有背景音乐，增加体验感。
6. 提供查询详细的宠物种类介绍功能，包括图片、特征、饲养需求等信息。

## 各模块功能展示
### 登录与注册模块
<img width="148" height="327" alt="image" src="https://github.com/user-attachments/assets/ccf77621-448b-4b33-a888-a872f8a0aa54" />
<img width="147" height="326" alt="image" src="https://github.com/user-attachments/assets/93d9ad12-fabb-4446-82ce-a961595ce366" />
<img width="147" height="327" alt="image" src="https://github.com/user-attachments/assets/78c8a559-a653-418e-b059-ffd4968db773" />

### 收藏和发布功能
<img width="150" height="333" alt="image" src="https://github.com/user-attachments/assets/e0ecc156-69d1-4e5b-88fe-5462b3674ae1" />
<img width="150" height="333" alt="image" src="https://github.com/user-attachments/assets/98aee000-d3fe-48f8-9c31-79934a6765b2" />
<img width="150" height="333" alt="image" src="https://github.com/user-attachments/assets/a1dc05f4-907f-4c58-ad0d-bc8010d72088" />

### 地图定位
<img width="148" height="329" alt="image" src="https://github.com/user-attachments/assets/1708eae1-5599-4967-a4d2-80a0d43e09b4" />
<img width="149" height="329" alt="image" src="https://github.com/user-attachments/assets/d8b456b5-3bc2-4b8a-96bd-91740e534d17" />
<img width="148" height="329" alt="image" src="https://github.com/user-attachments/assets/97e0fa65-234a-4c40-9ffb-d8a788ee02da" />

### 个人猫咪档案模块
<img width="206" height="459" alt="image" src="https://github.com/user-attachments/assets/de9f6ddc-9fd6-4280-b98f-55f59d38f853" />
<img width="207" height="459" alt="image" src="https://github.com/user-attachments/assets/63bc0890-ca73-481a-a13e-218b7fb4b1da" />

### 后台音乐模块

### 心情记录模块
<img width="162" height="360" alt="image" src="https://github.com/user-attachments/assets/840b4573-273f-4de9-96ab-4930d4bc5303" />

### 猫咪知识科普模块
<img width="199" height="441" alt="image" src="https://github.com/user-attachments/assets/4c519146-8d1c-44d8-bdb9-bcec4f7c6476" />
<img width="199" height="441" alt="image" src="https://github.com/user-attachments/assets/85f1262c-42b0-4714-9be6-3443128d08cb" />

### 数据库设计

1. 用户表：_user

| **字段** | **类型** | **长度** | **能否为****null** | **默认** | **注释** |
| -------- | -------- | -------- | ------------------ | -------- | -------- |
| _id      | integer  |          | 否                 | 自增     | 用户id   |
| username | varchar  | 20       | 否                 |          | 用户名   |
| password | varchar  | 20       | 否                 |          | 密码     |

2. 发现表：_find

| **字段** | **类型** | **长度** | **能否为****null** | **默认** | **注释** |
| -------- | -------- | -------- | ------------------ | -------- | -------- |
| _id      | integer  |          | 否                 | 自增     | 发现id   |
| title    | varchar  | 20       | 否                 |          | 名称     |
| url      | varchar  | 200      | 否                 |          | 图片url  |
| user_id  | integer  |          | 否                 |          | 用户id   |

3. 收藏表：_favorite

| **字段** | **类型** | **长度** | **能否为****null** | **默认** | **注释** |
| -------- | -------- | -------- | ------------------ | -------- | -------- |
| _id      | integer  |          | 否                 | 自增     | 收藏id   |
| user_id  | integer  |          | 否                 |          | 用户id   |
| find_id  | integer  |          | 否                 |          | 发现id   |

4. 心情记录表：_vaccine_log

| **字段**    | **类型** | **长度** | **能否为****null** | **默认** | **注释** |
| ----------- | -------- | -------- | ------------------ | -------- | -------- |
| _id         | integer  |          | 否                 | 自增     | 记录id   |
| user_id     | integer  |          | 否                 |          | 用户id   |
| content     | varchar  | 200      | 否                 |          | 记录内容 |
| create_time | varchar  | 20       | 否                 |          | 记录时间 |

