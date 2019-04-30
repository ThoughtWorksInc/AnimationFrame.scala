package com.thoughtworks.binding
import Binding._
import org.scalajs.dom.window
object NextAnimationFrame extends Binding[Option[Double]] {

  private var cache: Option[Double] = None

  private val publisher = new SafeBuffer[ChangedListener[Option[Double]]]

  override def value = cache

  override protected def removeChangedListener(listener: ChangedListener[Option[Double]]): Unit = {
    publisher -= listener
  }

  private var isHandlerRegistered: Boolean = false

  @inline
  private def handler(result: Double): Unit = {
    val newCache = Some(result)
    cache = newCache
    val event = new ChangedEvent[Option[Double]](this, newCache)
    for (listener <- publisher) {
      listener.changed(event)
    }
  }

  override protected def addChangedListener(listener: ChangedListener[Option[Double]]): Unit = {
    publisher += listener
    if (!isHandlerRegistered) {
      isHandlerRegistered = true
      window.requestAnimationFrame(handler)
    }
  }

}
