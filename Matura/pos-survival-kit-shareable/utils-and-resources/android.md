# Sending mails:
```kt
var intent = Intent(Intent.ACTION_SEND_MULTIPLE)
intent.setType("message/rfc822")
intent.putExtra(Intent.EXTRA_EMAIL, mail.receivers.map {r -> r.email }.toTypedArray() )
intent.putExtra(Intent.EXTRA_SUBJECT, mail.subject)
intent.putExtra(Intent.EXTRA_TEXT, mail.content)
var uris = ArrayList<Uri>()

if (mail.attachment1.length > 0) {
    uris.add(Uri.parse(mail.attachment1))
}

if (mail.attachment2.length > 0) {
    uris.add(Uri.parse(mail.attachment2))
}

if (mail.attachment3.length > 0) {
    uris.add(Uri.parse(mail.attachment3))
}

if (uris.size > 0) {
    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris)
}

try {
    startActivityForResult(Intent.createChooser(intent, "Sending Mails..."), 420)
}
catch (_: Throwable) {
    /* weird type issue */
}
```
