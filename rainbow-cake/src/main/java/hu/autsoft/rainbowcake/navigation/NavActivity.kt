package hu.autsoft.rainbowcake.navigation

import android.os.Bundle
import android.support.annotation.AnimRes
import android.support.annotation.AnimatorRes
import android.support.annotation.CallSuper
import hu.autsoft.rainbowcake.R
import hu.autsoft.rainbowcake.base.BaseActivity
import hu.autsoft.rainbowcake.base.BaseViewModel

/**
 * Activity base class with built-in Fragment based navigation support.
 */
abstract class NavActivity<VS : Any, VM : BaseViewModel<VS>> : BaseActivity<VS, VM>() {

    /**
     * The Navigator that subclasses can access to perform navigation actions.
     */
    val navigator: ExtendedNavigator
        get() = navigatorImpl

    /**
     * Private implementation of the Navigator interface with a more concrete
     * type for back press delegation.
     */
    private lateinit var navigatorImpl: NavigatorImpl

    @CallSuper
    override fun onBackPressed() = navigatorImpl.onBackPressed()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigatorImpl = NavigatorImpl(
                this,
                defaultEnterAnim,
                defaultExitAnim,
                defaultPopEnterAnim,
                defaultPopExitAnim
        )
    }

    /**
     * The default enter animation for the incoming Fragment in navigation
     * transitions.
     *
     * Used if no other animation is specified when calling [Navigator]
     * methods.
     *
     * Example: consider the transition of adding Fragment B on top of
     * Fragment A.
     *
     *                  <B>
     * <A> -- add B --> <A>
     *         ^
     *
     * This is the enter animation for B during this transition.
     */
    @AnimRes
    @AnimatorRes
    open val defaultEnterAnim: Int = R.anim.default_transition_in

    /**
     * The default enter animation for the outgoing Fragment in navigation
     * transitions.
     *
     * Used if no other animation is specified when calling [Navigator]
     * methods.
     *
     * Example: consider the transition of adding Fragment B on top of
     * Fragment A.
     *
     *                  <B>
     * <A> -- add B --> <A>
     *         ^
     *
     * This is the exit animation for A during this transition.
     */
    @AnimRes
    @AnimatorRes
    open val defaultExitAnim: Int = R.anim.default_transition_out

    /**
     * The default reenter animation for when the outgoing Fragment in
     * navigation transitions reappears after the incoming one is popped.
     *
     * Used if no other animation is specified when calling [Navigator]
     * methods.
     *
     * Example: consider the transition of adding Fragment B on top of
     * Fragment A.
     *
     *                  <B>
     * <A> -- add B --> <A> -- pop --> <A>
     *                          ^
     *
     * This is the enter animation for A when B is popped from the stack.
     */
    @AnimRes
    @AnimatorRes
    open val defaultPopEnterAnim: Int = R.anim.default_transition_in

    /**
     * The default exit animation for the incoming Fragment in navigation
     * transitions when it is popped from the stack.
     *
     * Used if no other animation is specified when calling [Navigator]
     * methods.
     *
     * Example: consider the transition of adding Fragment B on top of
     * Fragment A.
     *
     *                  <B>
     * <A> -- add B --> <A> -- pop --> <A>
     *                          ^
     *
     * This is the exit animation for B when it is popped from the stack.
     */
    @AnimRes
    @AnimatorRes
    open val defaultPopExitAnim: Int = R.anim.default_transition_out

}
