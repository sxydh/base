import wx
import datetime


class KeepFrame(wx.Frame):
    def __init__(self):
        wx.Frame.__init__(self, None, wx.ID_ANY, "Test", pos=(0, 0), size=(800, 600))
        self.panel = wx.Panel(self, -1, size=(800, 600))

        self.boxsizer = wx.BoxSizer(wx.VERTICAL)

        self.button = wx.Button(self.panel, -1, label="update component")
        self.Bind(wx.EVT_BUTTON, self.updatecomp, self.button)
        self.boxsizer.Add(self.button, flag=wx.ALL, border=5)

        self.SetSizer(self.boxsizer)
        self.Centre()
        self.Show(True)

    def updatecomp(self, event):
        text = wx.TextCtrl(self.panel, -1, size=(400, 100),
                           value=str(event) + "\r\n" + str(datetime.datetime.now()),
                           style=wx.TE_READONLY | wx.TE_MULTILINE)

        self.boxsizer.Add(text, flag=wx.ALL, border=5)

        for child in self.boxsizer.GetChildren():
            print(child)

        print()

        self.layout()

    def layout(self):
        self.Layout()
        upper = self.GetParent()
        while upper is not None:
            upper.Layout()
            upper = upper.GetParent()


def main():
    app = wx.App()
    frame = KeepFrame()
    frame.Show(True)
    app.MainLoop()


if __name__ == '__main__':
    main()
