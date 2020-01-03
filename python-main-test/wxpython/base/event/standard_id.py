import wx

"""
It is recommended to use standard identifiers. The identifiers can provide some standard graphics or behaviour on some 
platforms.
"""


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):
        pnl = wx.Panel(self)
        grid = wx.GridSizer(3, 2, 3, 3)

        grid.AddMany([(wx.Button(pnl, wx.ID_CANCEL), 0, wx.TOP | wx.LEFT, 9),
                      (wx.Button(pnl, wx.ID_DELETE), 0, wx.TOP, 9),
                      (wx.Button(pnl, wx.ID_SAVE), 0, wx.LEFT, 9),
                      (wx.Button(pnl, wx.ID_EXIT)),
                      (wx.Button(pnl, wx.ID_STOP), 0, wx.LEFT, 9),
                      (wx.Button(pnl, wx.ID_NEW))])

        self.Bind(wx.EVT_BUTTON, self.on_quit_app, id=wx.ID_EXIT)

        pnl.SetSizer(grid)

        self.SetTitle("Standard ids")
        self.Centre()

    def on_quit_app(self, event):
        print(event.GetId())
        self.Close()


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
