import wx


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):
        pnl = wx.Panel(self)
        closeButton = wx.Button(pnl, label='Close', pos=(20, 20))

        closeButton.Bind(wx.EVT_BUTTON, self.on_close)

        self.SetSize((350, 250))
        self.SetTitle('wx.Button')
        self.Centre()

    def on_close(self, e):
        self.Close(True)


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
