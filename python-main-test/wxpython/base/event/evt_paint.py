import wx

"""
A paint event is generated when a window is redrawn. This happens when we resize a window or when we maximize it. 
A paint event can be generated programatically as well. For example, when we call SetLabel() method to change a wx.
StaticText widget. Note that when we minimize a window, no paint event is generated.
"""


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):
        self.count = 0
        self.Bind(wx.EVT_PAINT, self.on_paint)

        self.SetTitle('Paint events')
        self.SetSize((350, 250))
        self.Centre()

    def on_paint(self, e):
        self.count += 1
        print(self.count)
        dc = wx.PaintDC(self)
        text = "Number of paint events: {0}".format(self.count)
        dc.DrawText(text, 20, 20)  # only minimization working


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
