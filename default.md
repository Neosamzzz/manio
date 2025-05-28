# API文档


**简介**:API文档


**HOST**:http://localhost:8080


**联系人**:


**Version**:v1.0


**接口路径**:/v3/api-docs


[TOC]






# 商品管理


## 所有商品


**接口地址**:`/ProductManage`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListProduct|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||array|Product|
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;description||string||
|&emsp;&emsp;price||number||
|&emsp;&emsp;categoryId||integer(int32)||
|&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;coverImg||string||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||


**响应示例**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": [
		{
			"id": 0,
			"name": "",
			"description": "",
			"price": 0,
			"categoryId": 0,
			"sort": 0,
			"coverImg": "",
			"status": 0,
			"createTime": ""
		}
	]
}
```


## 新增商品


**接口地址**:`/ProductManage`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "name": "",
  "description": "",
  "price": 0,
  "categoryId": 0,
  "sort": 0,
  "coverImg": "",
  "status": 0,
  "createTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|product|Product|body|true|Product|Product|
|&emsp;&emsp;id|||false|integer(int32)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;description|||false|string||
|&emsp;&emsp;price|||false|number||
|&emsp;&emsp;categoryId|||false|integer(int32)||
|&emsp;&emsp;sort|||false|integer(int32)||
|&emsp;&emsp;coverImg|||false|string||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": {}
}
```


## 修改商品


**接口地址**:`/ProductManage`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "name": "",
  "description": "",
  "price": 0,
  "categoryId": 0,
  "sort": 0,
  "coverImg": "",
  "status": 0,
  "createTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|product|Product|body|true|Product|Product|
|&emsp;&emsp;id|||false|integer(int32)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;description|||false|string||
|&emsp;&emsp;price|||false|number||
|&emsp;&emsp;categoryId|||false|integer(int32)||
|&emsp;&emsp;sort|||false|integer(int32)||
|&emsp;&emsp;coverImg|||false|string||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": {}
}
```


## 查询商品


**接口地址**:`/ProductManage/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultProduct|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||Product|Product|
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;description||string||
|&emsp;&emsp;price||number||
|&emsp;&emsp;categoryId||integer(int32)||
|&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;coverImg||string||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||


**响应示例**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": {
		"id": 0,
		"name": "",
		"description": "",
		"price": 0,
		"categoryId": 0,
		"sort": 0,
		"coverImg": "",
		"status": 0,
		"createTime": ""
	}
}
```


## 删除


**接口地址**:`/ProductManage/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": {}
}
```


# 用户相关接口


## 新增用户


**接口地址**:`/user`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "username": "",
  "name": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|用户数据传输对象|body|true|UserDTO|UserDTO|
|&emsp;&emsp;username|用户名||true|string||
|&emsp;&emsp;name|姓名||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": {}
}
```


# 分类


## 获取分类列表


**接口地址**:`/Category/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListCategory|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||array|Category|
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;imageUrl||string||
|&emsp;&emsp;parentId||integer(int32)||
|&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||


**响应示例**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": [
		{
			"id": 0,
			"name": "",
			"imageUrl": "",
			"parentId": 0,
			"sort": 0,
			"status": 0,
			"createTime": ""
		}
	]
}
```