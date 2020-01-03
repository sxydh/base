import wx

"""
When we press a key on our keyboard, a wx.KeyEvent is generated. This event is sent to the widget that has currently 
focus. There are three different key handlers:
    wx.EVT_KEY_DOWN
    wx.EVT_KEY_UP
    wx.EVT_CHAR
"""


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):

        pnl = wx.Panel(self)
        pnl.Bind(wx.EVT_KEY_DOWN, self.on_key_down)
        pnl.SetFocus()

        self.SetSize((350, 250))
        self.SetTitle('Key event')
        self.Centre()

    def on_key_down(self, e):

        key = e.GetKeyCode()

        if key == wx.WXK_ESCAPE:

            ret = wx.MessageBox('Are you sure to quit?', 'Question',
                                wx.YES_NO | wx.NO_DEFAULT, self)

            if ret == wx.YES:
                self.Close()


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
