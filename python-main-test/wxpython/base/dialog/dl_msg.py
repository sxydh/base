import wx


class Example(wx.Frame):

    def __init__(self, *args, **kwargs):
        super(Example, self).__init__(*args, **kwargs)

        self.init_ui()

    def init_ui(self):
        panel = wx.Panel(self)

        hbox = wx.BoxSizer()
        sizer = wx.GridSizer(2, 2, 2, 2)

        btn1 = wx.Button(panel, label='Info')
        btn2 = wx.Button(panel, label='Error')
        btn3 = wx.Button(panel, label='Question')
        btn4 = wx.Button(panel, label='Alert')

        sizer.AddMany([btn1, btn2, btn3, btn4])

        hbox.Add(sizer, 0, wx.ALL, 15)
        panel.SetSizer(hbox)

        btn1.Bind(wx.EVT_BUTTON, self.show_message1)
        btn2.Bind(wx.EVT_BUTTON, self.show_message2)
        btn3.Bind(wx.EVT_BUTTON, self.show_message3)
        btn4.Bind(wx.EVT_BUTTON, self.show_message4)

        self.SetSize((300, 200))
        self.SetTitle('Messages')
        self.Centre()

    def show_message1(self, event):
        dial = wx.MessageDialog(None, 'Download completed', 'Info', wx.OK)
        dial.ShowModal()

    def show_message2(self, event):
        dial = wx.MessageDialog(None, 'Error loading file', 'Error',
                                wx.OK | wx.ICON_ERROR)
        dial.ShowModal()

    def show_message3(self, event):
        dial = wx.MessageDialog(None, 'Are you sure to quit?', 'Question',
                                wx.YES_NO | wx.NO_DEFAULT | wx.ICON_QUESTION)
        dial.ShowModal()

    def show_message4(self, event):
        dial = wx.MessageDialog(None, 'Unallowed operation', 'Exclamation',
                                wx.OK | wx.ICON_EXCLAMATION)
        dial.ShowModal()


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
