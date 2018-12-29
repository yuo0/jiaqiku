import  tornado.web
import  tornado.ioloop
import  suds
from suds import client
from tornadoweb.phone import *
from tornadoweb.cacheutils import CacheService
import json


class IndexHandler(tornado.web.RequestHandler):
    # get请求  self:当前对象
    def get(self):
        print("接受到用户的get请求 ")
        # 写一个消息

        # url = "http://127.0.0.1:8009/user?wsdl"
        # service = suds.client.Client(url)
        # yma = service.service.yanma()
        # print("yma",yma)
        self.render("login.html", failmsg=None)


class userh(tornado.web.RequestHandler):
    def post(self):
        print("接受userh请求")
        method=self.get_argument("action")
        print("method",method)
        if method=="login":
           #请求头的信息：
           name=self.get_argument("username")
           pwd=self.get_argument("userpwd")
           print(name,pwd)
           url = "http://127.0.0.1:8009/user?wsdl"
           service = suds.client.Client(url)
           msg=service.service.quejianchadenglu(name,pwd)
           print("msg",msg)
           # 得到请求的头,区分跳转页面的数据格式
           headin=self.request.headers
           hinfo=headin["User-Agent"]
           print("头信息为：",hinfo)
           val=checkPCorMobile(hinfo)
           print("val",val)

           if msg=='登陆成功':
               #从缓存中获取策略  内部缓存
               cacheService = CacheService()
               jsonDatas = cacheService.getMenuData("tmenudata")
               
               if val=="PC":
                   self.render("index.html",contentdata=jsonDatas)
               else:
                   self.finish({"msg":"success","contentdata":jsonDatas})
           else:
               if val=="PC":
                   self.render("login.html",failmsg=msg)
               else:
                   self.finish({"msg":"fail"})
        elif method=="register":
            name = self.get_argument("username")
            pwd = self.get_argument("userpwd")
            # url = "http://127.0.0.1:8009/user?wsdl"
            # service = suds.client.Client(url)
            # msg = service.service.jianzhuce(name, pwd)
            # if msg=='可注册':
            #     self.render("register.html",failmsg=msg)
            # else:
            #     self.render("register.html",failmsg=msg)

class antvhandler(tornado.web.RequestHandler):
    def post(self, *args, **kwargs):
        print("接受到antv请求")
        method = self.get_argument("datas")
        if method == "nannvbili":
            url = "http://127.0.0.1:8009/user?wsdl"
            service = suds.client.Client(url)
            data = service.service.quesexshu()
            #print("data-->", data)
            jsonDatas = json.loads(data)
            print("jsonDatas-->", jsonDatas)
            self.finish({"jsonDatas": jsonDatas})

        # elif method=="zhiwubili":
        #     url = "http://127.0.0.1:8009/user?wsdl"
        #     service = suds.client.Client(url)
        #     datas = service.service.nzhiwu()
        #     print("datas---", datas)
        #     print(type(datas))
        #     jsondatas = json.loads(datas)
        #     print("jsondatas---", jsondatas)
        #     self.finish({"jsondata": jsondatas})




settings={ "template_path":"template",
    "static_path":"static",
     }


app=tornado.web.Application([(r'/',IndexHandler),
                             (r'/user',userh),],
                            (r'/antv',antvhandler),
                            **settings)


if __name__=="__main__":
    app.listen(8100)
    tornado.ioloop.IOLoop.current().start()