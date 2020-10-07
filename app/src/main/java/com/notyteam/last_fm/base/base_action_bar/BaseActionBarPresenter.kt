package noty_team.com.masterovik.base.base_action_bar

import io.reactivex.disposables.CompositeDisposable

class BaseActionBarPresenter(var actionBarView: ActionBarContract.View) : ActionBarContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun setupView() {

    }

    override fun setupActions() {
        actionBarView.showActionBar(false)
        disposable.add(actionBarView.leftButtonAction().subscribe { leftButtonAction() })
        disposable.add(actionBarView.rightButtonAction().subscribe { rightButtonAction() })
    }


    override fun leftButtonAction() {


    }

    override fun rightButtonAction() {

    }

    override fun dispose() {
        disposable.clear()
    }

    override fun start() {
        setupView()
        setupActions()
    }

    override fun stop() {
        dispose()
    }
}