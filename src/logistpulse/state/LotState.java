package logistpulse.state;

public interface LotState {
    String name();
    boolean canPick();
    LotState reserve();
    LotState release();
    LotState quarantine();
}
