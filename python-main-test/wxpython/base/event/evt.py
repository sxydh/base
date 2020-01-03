import wx

"""
Event is a piece of application-level information from the underlying framework, typically the GUI toolkit. Event loop 
is a programming construct that waits for and dispatches events or messages in a program. The event loop repeatedly 
looks for events to process. A dispatcher is a process which maps events to event handlers. Event handlers are methods 
that react to events.

Event object is an object associated with the event. It is usually a window. Event type is a unique event that has been 
generated. Event binder is an object that binds an event type with an event handler.

The three steps to work with events in wxPython are:
    Identify the event binder name: wx.EVT_SIZE, wx.EVT_CLOSE etc.
    Create an event handler. This method is called when an event is generated.
    Bind an event to an event handler.
    
In wxPython we say to bind a method to an event. Sometimes a word hook is used. You bind an event by calling the Bind() 
method. The method has the following parameters:
    Bind(event, handler, source=None, id=wx.ID_ANY, id2=wx.ID_ANY)
The event is one of EVT_* objects. It specifies the type of the event. The handler is an object to be called. In other 
words, it is a method that a programmer binds to an event. The source parameter is used when we want to differentiate 
between the same event type from different widgets. The id parameter is used when we have multiple buttons, menu items 
etc. The id is used to differentiate among them. The id2 is used when it is desirable to bind a handler to a range 
of ids, such as with EVT_MENU_RANGE.

Note that method Bind() is defined in class EvtHandler. It is a class from which wx.Window inherits. wx.Window is a base 
class for most widgets in wxPython. There is also a reverse process. If we want to unbind a method from an event, 
we call the Unbind() method. It has the same parameters as the above one.
"""


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):
        wx.StaticText(self, label='x:', pos=(10, 10))
        wx.StaticText(self, label='y:', pos=(10, 30))

        self.st1 = wx.StaticText(self, label='', pos=(30, 10))
        self.st2 = wx.StaticText(self, label='', pos=(30, 30))

        self.Bind(wx.EVT_MOVE, self.on_move)

        self.SetSize((350, 250))
        self.SetTitle('Move event')
        self.Centre()

    """
    The event parameter in the on_move() method is an object specific to a particular event type. In our case it is the 
    instance of a wx.MoveEvent class. This object holds information about the event. For example the event object or 
    the position of the window. In our case the event object is the wx.Frame widget. We can find out the current 
    position by calling the GetPosition() method of the event.
    """
    def on_move(self, e):
        x, y = e.GetPosition()
        self.st1.SetLabel(str(x))
        self.st2.SetLabel(str(y))


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
