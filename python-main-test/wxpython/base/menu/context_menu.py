import wx

"""
A context menu is a list of commands that appears under some context. 
For example, in a Firefox web browser, when we right click on a web page, we get a context menu. 
Here we can reload a page, go back or view page source. If we right click on a toolbar, we get another context menu for 
managing toolbars. Context menus are sometimes called popup menus.
"""


class MyPopupMenu(wx.Menu):

    def __init__(self, parent):
        super(MyPopupMenu, self).__init__()

        self.parent = parent

        mmi = wx.MenuItem(self, wx.NewId(), 'Minimize')
        self.Append(mmi)
        self.Bind(wx.EVT_MENU, self.on_minimize, mmi)

        cmi = wx.MenuItem(self, wx.NewId(), 'Close')
        self.Append(cmi)
        self.Bind(wx.EVT_MENU, self.on_close, cmi)

    def on_minimize(self, e):
        self.parent.Iconize()

    def on_close(self, e):
        self.parent.Close()


class Example(wx.Frame):

    def __init__(self, *args, **kwargs):
        super(Example, self).__init__(*args, **kwargs)

        self.init_ui()

    def init_ui(self):
        self.Bind(wx.EVT_RIGHT_DOWN, self.on_right_down)

        self.SetSize((350, 250))
        self.SetTitle('Context menu')
        self.Centre()

    def on_right_down(self, e):
        self.PopupMenu(MyPopupMenu(self), e.GetPosition())


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
