import wx

"""
Window identifiers are integers that uniquely determine the window identity in the event system. There are three ways 
to create window ids: 
    let the system automatically create an id
    use standard identifiers
    create your own id
    
wx.Button(parent, -1)
wx.Button(parent, wx.ID_ANY)
If we provide -1 or wx.ID_ANY for the id parameter, we let the wxPython automatically create an id for us. 
The automatically created id's are always negative, whereas user specified ids must always be positive. We usually use 
this option when we do not need to change the widget state. For example a static text that will never be changed during 
the life of the application. We can still get the id if we want. There is a method GetId() which determines the id.
"""


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):
        pnl = wx.Panel(self)
        exitButton = wx.Button(pnl, wx.ID_ANY, 'Exit', (10, 10))

        self.Bind(wx.EVT_BUTTON, self.on_exit, id=exitButton.GetId())  # 只接受id为exitButton.GetId()的button事件

        self.SetTitle("Automatic ids")
        self.Centre()

    def on_exit(self, event):
        print(event.GetId())
        self.Close()


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
