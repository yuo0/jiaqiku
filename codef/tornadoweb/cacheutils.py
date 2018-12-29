import suds
from  suds  import  client
from tornadoweb.phone import *
import json

#菜单数据是不经常变化的，我们应从缓存中获取，不应每次从数据库中响应,
#减少对数据库服务器的负载。
class CacheService():

    #全局静态数据
    cachedata={}

    def __init__(self):
        print("----产生一个对象初始化---")
        #定义一个缓存的属性 {键:值}
        #self.cachedata={}

    def   getMenuData(self,key):

           #判断键在不在
          if  key  in self.cachedata:
              print("缓存中有数据**********")
              return  self.cachedata[key]
          else:
              print("缓存中么数据----------")
              url = "http://127.0.0.1:8009/user?wsdl"
              service = suds.client.Client(url)
              data = service.service.queryMenu()
              print("data-->", data)
              print(type(data))
              jsonDatas = json.loads(data)
              print("jsonDatas-->", jsonDatas)
              self.cachedata[key]=jsonDatas
              return jsonDatas