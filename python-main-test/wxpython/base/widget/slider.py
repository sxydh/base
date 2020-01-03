import wx


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):
        pnl = wx.Panel(self)

        sizer = wx.GridBagSizer(5, 5)

        """
        A wx.Slider is created. We provide the initial position of the slider with the value parameter and the minimum 
        and maximum slider positions with the minValue and maxValue parameters. The wx.SL_HORIZONTAL makes the slider 
        to be horizontal.
        """
        sld = wx.Slider(pnl, value=200, minValue=150, maxValue=500,
                        style=wx.SL_HORIZONTAL)

        sld.Bind(wx.EVT_SCROLL, self.on_slider_scroll)
        sizer.Add(sld, pos=(0, 0), flag=wx.ALL | wx.EXPAND, border=25)

        self.txt = wx.StaticText(pnl, label='200')
        sizer.Add(self.txt, pos=(0, 1), flag=wx.TOP | wx.RIGHT, border=25)

        sizer.AddGrowableCol(0)
        pnl.SetSizer(sizer)

        self.SetTitle('wx.Slider')
        self.Centre()

    def on_slider_scroll(self, e):
        obj = e.GetEventObject()
        val = obj.GetValue()

        self.txt.SetLabel(str(val))


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
