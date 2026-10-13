Sakib's ICT — HTML Practice Lab Android App Project

এই ZIP-এ আগের HTML Practice Lab-টি Android অ্যাপের ভেতরে চালানোর জন্য প্রস্তুত Android Studio project রাখা হয়েছে। HTML, বাংলা টিউটোরিয়াল, প্র্যাকটিস টাস্ক ও কুইজ app-এর সঙ্গে bundled, তাই মূল ফিচার চালাতে ইন্টারনেট লাগে না। বাইরের লিংক/অনলাইন ছবি খুলতে ইন্টারনেট লাগতে পারে।

APK তৈরি করার সহজ পথ (কম্পিউটার লাগবে):
1. ZIP extract করুন।
2. Android Studio ইনস্টল করে Open চাপুন এবং extracted SakibsICT_HTML_Android ফোল্ডার নির্বাচন করুন।
3. Gradle sync শেষ হলে Build > Build Bundle(s) / APK(s) > Build APK(s) চাপুন।
4. APK সাধারণত app/build/outputs/apk/debug/app-debug.apk-এ পাওয়া যাবে।

GitHub Actions পথ:
1. ZIP extract করে ভেতরের সব ফাইল একটি নতুন GitHub repository-তে upload করুন।
2. Actions tab-এ যান এবং “Build Android APK” workflow চালান (অথবা main branch-এ push করুন)।
3. Workflow শেষ হলে Artifacts থেকে Sakibs-ICT-HTML-Practice-APK ডাউনলোড করুন; ZIP খুললে APK পাবেন।

গুরুত্বপূর্ণ: এই পরিবেশে Android SDK/Gradle build tool নেই, তাই এখানে সরাসরি APK compile ও test করা যায়নি। এটি Android Studio project source, compiled APK নয়।
