# TextNowPlus

**Disclaimer: This is a fan project, and not affiliated with or endorsed
by TextNow.**

TextNowPlus is a fork of VoicePlus.

VoicePlus is a system-level app that allows any standard Android SMS
messaging app to be used for sending and receiving Google Voice texts.

TextNowPlus aspires to build on the features of Voiceplus, but using the
services of another company, TextNow. My original goal was to allow
the use of TextSecure (a wonderful secure messaging tool) with my TextNow
phone service, which is otherwise sandboxed into it's own messaging app.

In the longer term, I hope to add:

- MMS support [(1)][1]
- Native Android SIP, automatically configured (standard TextNow rates
  will still apply) [(2)][2]
- Non-Google push notifications [(3)][3]
- More standalone functionality (monitor balance [(4)][4], sign
  in/out [(5)][5])

### Usage

This app is a system app,and needs to be built as part of Cyanogenmod's
build process, in place of the VoicePlus repo. There are no pre-compiled
APK's for now, and I won't have instructions here for awhile. Also, it
can't coexist with VoicePlus at the moment. And also, credentials need
to be manually sniffed on the wire via `mitmproxy`, and the `username`
and `client_id` manually entered into the settings XML file. Yeah, this
isn't user friendly yet. Just wanted to get some feedback on the ideas.

### Background

TextNow is a commercial company offering an app that provides voice and
SMS/MMS service over data, reachable via regular phone numbers. They
have started a small mobile carrier that will offer data-only phone
plans using their app. On the backend, their free SMS service is powered
by an unofficial API, and their per-minute voice service is powered by
standard SIP.

### Motivations & Intentions

I'd like to help make it possible for people to easily communicate using
the cheap and plentiful wifi in many urban areas.

Perhaps more importantly, I'd like to see more people prioritizing data
channels for communication, as a means to improve our overall privacy
options. For that statement to make sense, it's important to understand
that the regular phone network is inherently broken.  Its security was
cracked by grad students in the 90s and never fixed.  The only security
that we can hope for is security that only becomes available over data,
through end-to-end encryption.  And when we can rely on data-only for
both secure and insecure communication, then it becomes trivial to
opportunistically upgrade to encrypted channels when our tools detect
that both parties are capable.

So why not just use regular phone providers with standard data plans
offered separately. You see, when we choose to rely more on data channels, both
encrypted and unencrypted communications are going through similar
bottlenecks.
This might sound bad, but this is a good situation to find ourselves in! The shared problem aligns
incentives toward making both insecure and secure communications better.
As it stands, communication is
mentally "tiered" in most peoples' minds: *reliable* cell communications, and *unreliable* data
communications. But on the backend, it's all data. Phone company's just
prioritize and shape regular cell communications (text & voice), while
remaining laisez-faire with all the other data traffic lumped together.

But when using data for *all* communications, solving reliability issues
for *insecure* calls (to "regular" numbers) also helps solve reliability
issues for *secure* calls. And now, a provider like TextNow who has
become a carrier, becomes interesting. Though not selling security, they
are still selling hardware and **data-only service** with expectations of
*reliability*. And so their goals become aligned with those seeking
better privacy solutions, even if TextNow is not working on those
solutions themselves.

[1]: https://github.com/patcon/TextNowPlus-android/issues/1
[2]: https://github.com/patcon/TextNowPlus-android/issues/7
[3]: https://github.com/patcon/TextNowPlus-android/issues/8
[4]: https://github.com/patcon/TextNowPlus-android/issues/9
[5]: https://github.com/patcon/TextNowPlus-android/issues/6
