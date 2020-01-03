import wx


class Example(wx.Frame):

    def __init__(self, parent, title):
        super(Example, self).__init__(parent, title=title)

        self.init_ui()
        self.Centre()

    def init_ui(self):
        panel = wx.Panel(self)
        sizer = wx.GridBagSizer(4, 4)

        text = wx.StaticText(panel, label="Rename To")
        sizer.Add(text, pos=(0, 0), flag=wx.TOP | wx.LEFT | wx.BOTTOM, border=5)

        tc = wx.TextCtrl(panel)
        """
        The wx.TextCtrl goes to the beginning of the second row (1, 0). Remember, that we count from zero. 
        It expands 1 row and 5 columns (1, 5). And we put 5 pixels of space to the left and to the right of the widget.
        """
        sizer.Add(tc, pos=(1, 0), span=(1, 5),
                  flag=wx.EXPAND | wx.LEFT | wx.RIGHT, border=5)

        buttonOk = wx.Button(panel, label="Ok", size=(90, 28))
        buttonClose = wx.Button(panel, label="Close", size=(90, 28))
        """
        We put two buttons into the fourth row. The third row is left empty, so that we have some space between the wx.
        TextCtrl and the buttons. We put the OK button into the fourth column and the Close button into the fifth one. 
        Notice that once we apply some space to one widget, it is applied to the whole row. That's why we did not specify 
        bottom space for the OK button. A careful reader might notice that we did not specify any space between the two 
        buttons; that is, we did not put any space to the right of the OK button, or to the right of the Close button. 
        In the constructor of the wx.GridBagSizer, we put some space between all widgets. So there is some space already.
        """
        sizer.Add(buttonOk, pos=(3, 3))
        sizer.Add(buttonClose, pos=(3, 4), flag=wx.RIGHT | wx.BOTTOM, border=10)

        sizer.AddGrowableCol(2)  # any value in the cols range
        sizer.AddGrowableRow(2)
        panel.SetSizer(sizer)


def main():
    app = wx.App()
    ex = Example(None, title='Rename')
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
