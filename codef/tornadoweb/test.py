import  tornado.web
import  tornado.ioloop
'''
class IndexHandler(tornado.web.RequestHandler):

    #get请求 self当前对象
    def  get(self):
        print("ssss：")
        self.write("咕咕咕咕")'''



class UserH(tornado.web.RequestHandler):
    def get(self):
        print("接受userindex")
        self.render("login.html")


class admin(tornado.web.RequestHandler):
    def post(self):
        print("接受que")
        #区分浏览器手机
        #得到请求头
        head=self.request.headers
        headin=head["User-Agent"]
        print("头信息",headin)
        self.finish("完成post请求")


class IndexHandler(tornado.web.RequestHandler):
    # get请求  self:当前对象
    def get(self):
        print("接受到用户的get请求 ")
        # 写一个消息
        self.render("login.html", failmsg=None);

#设置配置项
set={"template_path":"template","static_path":"static","static_url_prefix":"static"}

#创建一个应用对象
app=tornado.web.Application([(r'/',IndexHandler),(r'/userindex',UserH),(r'/que',admin)],(r'/', IndexHandler),**set)



if __name__=="__main__":
    #绑定一个监听端口，内网穿透保持一致
    app.listen(8001)
    #启动web，开始监听端口的链接
    tornado.ioloop.IOLoop.current().start()