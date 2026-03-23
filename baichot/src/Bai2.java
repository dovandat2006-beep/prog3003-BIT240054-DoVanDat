public class Bai2 {
    interface MessageService {
        void sendMessage(String message);
    }

    static class EmailService implements MessageService {
        @Override
        public void sendMessage(String message) {
            System.out.println("Gui email: " + message);
        }
    }

    static class SMSService implements MessageService {
        @Override
        public void sendMessage(String message) {
            System.out.println("Gui SMS: " + message);
        }
    }

    static class Notification {
        private MessageService messageService;

        public void setMessageService(MessageService messageService) {
            this.messageService = messageService;
        }

        public void sendNotification(String message) {
            if (messageService == null) {
                throw new IllegalStateException("MessageService chua duoc inject.");
            }

            messageService.sendMessage(message);
        }
    }

    public static void main(String[] args) {
        Notification notification = new Notification();

        System.out.println("===== BAI 2: SETTER INJECTION =====");

        notification.setMessageService(new EmailService());
        notification.sendNotification("Thong bao qua Email");

        notification.setMessageService(new SMSService());
        notification.sendNotification("Thong bao qua SMS");
    }
}
