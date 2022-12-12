package dao;

public class DaoAppSubscriber {
    DaoEventManager m;
    DaoEventUpdater u;
    DaoNewLocationDemat ld;
    DaoNewLocationPhys lp;
    DaoReturnLocationDamaged rd;
    DaoReturnLocationCorrect rc;
    
    public DaoAppSubscriber() {
        m = new DaoEventManager();
        u = new DaoEventUpdater();
        ld = new DaoNewLocationDemat();
        lp = new DaoNewLocationPhys();
        rd = new DaoReturnLocationDamaged();
        rc = new DaoReturnLocationCorrect();
        m.subscribe(EventType.UPDATE_SUBSCRIBER_EVENT_DAO, u);
        m.subscribe(EventType.RETURN_DAMAGED_EVENT_DAO, rd);
        m.subscribe(EventType.RETURN_CORRECT_EVENT_DAO, lp);
        m.subscribe(EventType.RENT_BLURAY_EVENT_DAO, ld);
        m.subscribe(EventType.QRCODE_RENT_EVENT_DAO, rc);
    }
}