import wx


class Example(wx.Frame):

    def __init__(self, parent, title):
        super(Example, self).__init__(parent, title=title)

        self.init_ui()
        self.Centre()
        self.Show()

    def init_ui(self):
        panel = wx.Panel(self)

        hbox = wx.BoxSizer(wx.HORIZONTAL)

        """
        This sizer is similar to wx.GridSizer. It does also lay out its widgets in a two dimensional table. 
        It adds some flexibility to it. wx.GridSizer cells are of the same size. All cells in wx.FlexGridSizer have the 
        same height in a row. All cells have the same width in a column. But all rows and columns are not necessarily 
        the same height or width.
        """
        fgs = wx.FlexGridSizer(3, 2, 9, 25)  # wx.FlexGridSizer(int rows=1, int cols=0, int vgap=0, int hgap=0)

        title = wx.StaticText(panel, label="Title")
        author = wx.StaticText(panel, label="Author")
        review = wx.StaticText(panel, label="Review")

        tc1 = wx.TextCtrl(panel)
        tc2 = wx.TextCtrl(panel)
        tc3 = wx.TextCtrl(panel, style=wx.TE_MULTILINE)

        fgs.AddMany(
            [title, (tc1, 1, wx.EXPAND), author, (tc2, 1, wx.EXPAND), (review, 1, wx.EXPAND), (tc3, 1, wx.EXPAND)])

        fgs.AddGrowableRow(2, 1)
        fgs.AddGrowableCol(1, 1)

        hbox.Add(fgs, proportion=1, flag=wx.ALL | wx.EXPAND, border=15)
        panel.SetSizer(hbox)


def main():
    app = wx.App()
    ex = Example(None, title='Review')
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
