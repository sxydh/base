import wx

"""
构造函数:
wx.TextCtrl(parent, 父窗口部件
            id, 标识符, 使用-1可以自动创建一个唯一的标识
            value='', 显示的内容
            pos=wx.DefaultPostion, 一个wx.Point或一个Python元组, 窗口部件的位置
            size=wx.DefaultSize, 一个wx.Size或一个Python元组, 窗口部件的尺寸
            style=0, 样式标记, 参考http://xoomer.virgilio.it/infinity77/wxPython/Widgets/wx.TextCtrl.html#__init__
            validator=wx.DefaultValidator, 内容校验器
            name=wx.TextCtrlNameStr) 对象的名字
其它API见http://xoomer.virgilio.it/infinity77/wxPython/Widgets/wx.TextCtrl.html#__init__
"""


class TextCtrlExampleFrame(wx.Frame):
    def __init__(self):
        wx.Frame.__init__(self, None, wx.ID_ANY, "TextCtrl example", pos=(0, 0), size=(800, 600))
        panel = wx.Panel(self, -1)

        # 输入文本居中对齐
        wx.TextCtrl(panel, wx.ID_ANY, pos=(0, 30), size=(200, 20), style=wx.TE_CENTER)

        # 显示密码文本框
        wx.TextCtrl(panel, wx.ID_ANY, pos=(0, 60), size=(200, 20), style=wx.TE_PASSWORD)

        # 显示只读文本
        wx.TextCtrl(panel, wx.ID_ANY, pos=(0, 90), size=(200, 20), style=wx.TE_READONLY)

        # 多行文本
        wx.TextCtrl(panel, wx.ID_ANY, pos=(0, 120), size=(200, 60), style=wx.TE_MULTILINE)

        # window下富文本
        wx.TextCtrl(panel, wx.ID_ANY, pos=(0, 190), size=(200, 60), style=wx.TE_RICH)


def main():
    app = wx.App()
    frame = TextCtrlExampleFrame()
    frame.Show(True)
    app.MainLoop()


if __name__ == '__main__':
    main()
