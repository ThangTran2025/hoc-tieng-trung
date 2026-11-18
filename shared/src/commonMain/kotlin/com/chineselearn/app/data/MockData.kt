package com.chineselearn.app.data

data class DictionaryEntry(
    val id: String,
    val hanzi: String,
    val pinyin: String,
    val hanViet: String,
    val meaning: String,
    val meaningEn: String,
    val radical: String,
    val strokes: Int,
    val exampleHanzi: String,
    val examplePinyin: String,
    val exampleMeaning: String,
    val exampleMeaningEn: String
)

data class GrammarExample(
    val hanzi: String,
    val pinyin: String,
    val meaning: String,
    val meaningEn: String
)

data class GrammarTopic(
    val id: String,
    val level: String, // HSK 1, HSK 2, etc.
    val title: String,
    val titleEn: String,
    val formula: String,
    val formulaEn: String = "",
    val explanation: String,
    val explanationEn: String,
    val examples: List<GrammarExample> = emptyList(),
    val exampleHanzi: String = "",
    val examplePinyin: String = "",
    val exampleMeaning: String = "",
    val exampleMeaningEn: String = ""
)

data class SentenceItem(
    val id: String,
    val hanzi: String,
    val pinyin: String,
    val meaning: String,
    val meaningEn: String
)

data class SentenceCategory(
    val id: String,
    val title: String,
    val titleEn: String,
    val iconEmoji: String,
    val description: String,
    val descriptionEn: String,
    val sentences: List<SentenceItem>
)

data class LyricLine(
    val timeMs: Long,
    val hanzi: String,
    val pinyin: String,
    val meaning: String,
    val meaningEn: String
)

data class KaraokeSong(
    val id: String,
    val title: String,
    val titleEn: String,
    val pinyinTitle: String,
    val artist: String,
    val durationText: String,
    val lyrics: List<LyricLine>
)

object MockData {
    val sampleGrammar = listOf(
        GrammarTopic(
            id = "g1_1",
            level = "HSK 1",
            title = "Câu chữ 是 (shì) - Câu phán đoán",
            titleEn = "Sentence structure with 是 (shì) - Judgment sentence",
            formula = "Chủ ngữ + 是 + Danh từ / Đại từ",
            formulaEn = "Subject + 是 + Noun / Pronoun",
            explanation = "Dùng để khẳng định danh tính, nghề nghiệp, quốc tịch, tương đương với 'là' trong tiếng Việt hoặc 'to be' trong tiếng Anh.",
            explanationEn = "Used to confirm identity, occupation, or nationality, equivalent to 'to be' (am, is, are).",
            examples = listOf(
                GrammarExample(
                    hanzi = "我是越南人，他是中国人。",
                    pinyin = "Wǒ shì yuènán rén, tā shì zhōngguó rén.",
                    meaning = "Tôi là người Việt Nam, anh ấy là người Trung Quốc.",
                    meaningEn = "I am Vietnamese, he is Chinese."
                ),
                GrammarExample(
                    hanzi = "王先生是我们的汉语老师。",
                    pinyin = "Wáng xiānsheng shì wǒmen de hànyǔ lǎoshī.",
                    meaning = "Thầy Vương là giáo viên tiếng Trung của chúng tôi.",
                    meaningEn = "Mr. Wang is our Chinese teacher."
                ),
                GrammarExample(
                    hanzi = "这是一本非常好看的书。",
                    pinyin = "Zhè shì yì běn fēicháng hǎokàn de shū.",
                    meaning = "Đây là một quyển sách rất hay.",
                    meaningEn = "This is a very good book."
                ),
                GrammarExample(
                    hanzi = "今天不是星期天，今天是星期六。",
                    pinyin = "Jīntiān bú shì xīngqītiān, jīntiān shì xīngqīliù.",
                    meaning = "Hôm nay không phải Chủ nhật, hôm nay là Thứ bảy.",
                    meaningEn = "Today is not Sunday, today is Saturday."
                )
            ),
            exampleHanzi = "我是越南人，他是中国人。",
            examplePinyin = "Wǒ shì yuènán rén, tā shì zhōngguó rén.",
            exampleMeaning = "Tôi là người Việt Nam, anh ấy là người Trung Quốc.",
            exampleMeaningEn = "I am Vietnamese, he is Chinese."
        ),
        GrammarTopic(
            id = "g1_2",
            level = "HSK 1",
            title = "Trợ từ nghi vấn 吗 (ma) - Câu hỏi Có / Không",
            titleEn = "Question particle 吗 (ma) - Yes/No questions",
            formula = "Câu khẳng định + 吗？",
            formulaEn = "Statement + 吗?",
            explanation = "Đặt ở cuối câu trần thuật để biến câu thành câu hỏi Yes/No (phải không, không?).",
            explanationEn = "Placed at the end of a statement to turn it into a Yes/No question.",
            examples = listOf(
                GrammarExample(
                    hanzi = "你好吗？汉语难吗？",
                    pinyin = "Nǐ hǎo ma? Hànyǔ nán ma?",
                    meaning = "Bạn khỏe không? Tiếng Trung có khó không?",
                    meaningEn = "How are you? Is Chinese difficult?"
                ),
                GrammarExample(
                    hanzi = "你想喝一杯热茶吗？",
                    pinyin = "Nǐ xiǎng hē yì bēi rè chá ma?",
                    meaning = "Bạn có muốn uống một ly trà nóng không?",
                    meaningEn = "Would you like to drink a cup of hot tea?"
                ),
                GrammarExample(
                    hanzi = "你明天去学校上课吗？",
                    pinyin = "Nǐ míngtiān qù xuéxiào shàngkè ma?",
                    meaning = "Ngày mai bạn có đến trường đi học không?",
                    meaningEn = "Are you going to school for classes tomorrow?"
                )
            ),
            exampleHanzi = "你好吗？汉语难吗？",
            examplePinyin = "Nǐ hǎo ma? Hànyǔ nán ma?",
            exampleMeaning = "Bạn khỏe không? Tiếng Trung có khó không?",
            exampleMeaningEn = "How are you? Is Chinese difficult?"
        ),
        GrammarTopic(
            id = "g1_3",
            level = "HSK 1",
            title = "Trợ từ kết cấu 的 (de) - Quan hệ sở hữu",
            titleEn = "Structural particle 的 (de) - Possessive relationship",
            formula = "Định ngữ + 的 + Trung tâm ngữ (Danh từ)",
            formulaEn = "Modifier + 的 + Head Noun",
            explanation = "Biểu thị quan hệ sở hữu ('của') hoặc bổ nghĩa miêu tả tính chất cho danh từ chính.",
            explanationEn = "Indicates possession ('s) or descriptive modifier for a head noun.",
            examples = listOf(
                GrammarExample(
                    hanzi = "这是我的汉语老师。",
                    pinyin = "Zhè shì wǒ de hànyǔ lǎoshī.",
                    meaning = "Đây là giáo viên tiếng Trung của tôi.",
                    meaningEn = "This is my Chinese teacher."
                ),
                GrammarExample(
                    hanzi = "他的电脑在桌子上。",
                    pinyin = "Tā de diànnǎo zài zhuōzi shàng.",
                    meaning = "Máy tính của anh ấy ở trên bàn.",
                    meaningEn = "His computer is on the table."
                ),
                GrammarExample(
                    hanzi = "红色的衣服很漂亮。",
                    pinyin = "Hóngsè de yīfu hěn piàoliang.",
                    meaning = "Quần áo màu đỏ rất đẹp.",
                    meaningEn = "Red clothes are very beautiful."
                )
            ),
            exampleHanzi = "这是我的汉语老师。",
            examplePinyin = "Zhè shì wǒ de hànyǔ lǎoshī.",
            exampleMeaning = "Đây là giáo viên tiếng Trung của tôi.",
            exampleMeaningEn = "This is my Chinese teacher."
        ),
        GrammarTopic(
            id = "g1_4",
            level = "HSK 1",
            title = "Phó từ phủ định 不 (bù) và 没 (méi)",
            titleEn = "Negative adverbs 不 (bù) and 没 (méi)",
            formula = "不 + Động từ / Tính từ  |  没 + 有 + Danh từ",
            formulaEn = "不 + Verb / Adjective  |  没 + 有 + Noun",
            explanation = "'不' dùng phủ định thói quen, hiện tại, tương lai, ý chí; '没' dùng phủ định sự việc quá khứ hoặc sở hữu (không có).",
            explanationEn = "'不' negates present, habitual, future actions or adjectives; '没' negates past actions or possession with 有.",
            examples = listOf(
                GrammarExample(
                    hanzi = "我不喝咖啡，我没有钱。",
                    pinyin = "Wǒ bù hē kāfēi, wǒ méiyǒu qián.",
                    meaning = "Tôi không uống cà phê, tôi không có tiền.",
                    meaningEn = "I don't drink coffee, I don't have money."
                ),
                GrammarExample(
                    hanzi = "昨天我没有去超市买东西。",
                    pinyin = "Zuótiān wǒ méiyǒu qù chāoshì mǎi dōngxi.",
                    meaning = "Hôm qua tôi đã không đi siêu thị mua đồ.",
                    meaningEn = "Yesterday I did not go to the supermarket to buy things."
                ),
                GrammarExample(
                    hanzi = "他不喜欢吃太辣的菜。",
                    pinyin = "Tā bù xǐhuan chī tài là de cài.",
                    meaning = "Anh ấy không thích ăn món quá cay.",
                    meaningEn = "He doesn't like eating food that is too spicy."
                )
            ),
            exampleHanzi = "我不喝咖啡，我没有钱。",
            examplePinyin = "Wǒ bù hē kāfēi, wǒ méiyǒu qián.",
            exampleMeaning = "Tôi không uống cà phê, tôi không có tiền.",
            exampleMeaningEn = "I don't drink coffee, I don't have money."
        ),
        GrammarTopic(
            id = "g1_5",
            level = "HSK 1",
            title = "Câu chữ 有 (yǒu) - Tồn tại & Sở hữu",
            titleEn = "Sentence with 有 (yǒu) - Possession & Existence",
            formula = "Chủ ngữ (Địa điểm) + 有 + Tân ngữ",
            formulaEn = "Subject (Location) + 有 + Object",
            explanation = "Biểu thị quyền sở hữu (ai có cái gì) hoặc biểu thị sự tồn tại (ở đâu có cái gì). Phủ định là '没有'.",
            explanationEn = "Expresses ownership (have/has) or existence (there is/are). Negative form is '没有'.",
            examples = listOf(
                GrammarExample(
                    hanzi = "桌子上有三本书。",
                    pinyin = "Zhuōzi shàng yǒu sān běn shū.",
                    meaning = "Trên bàn có ba quyển sách.",
                    meaningEn = "There are three books on the table."
                ),
                GrammarExample(
                    hanzi = "我家有五口人：爸爸、妈妈和我。",
                    pinyin = "Wǒ jiā yǒu wǔ kǒu rén: bàba, māma hé wǒ.",
                    meaning = "Nhà tôi có 5 người: bố, mẹ và tôi.",
                    meaningEn = "My family has five members: dad, mom, and me."
                ),
                GrammarExample(
                    hanzi = "学校里有一个很大的图书馆。",
                    pinyin = "Xuéxiào lǐ yǒu yí gè hěn dà de túshūguǎn.",
                    meaning = "Trong trường có một thư viện rất lớn.",
                    meaningEn = "There is a very large library in the school."
                )
            ),
            exampleHanzi = "桌子上有三本书。",
            examplePinyin = "Zhuōzi shàng yǒu sān běn shū.",
            exampleMeaning = "Trên bàn có ba quyển sách.",
            exampleMeaningEn = "There are three books on the table."
        ),
        GrammarTopic(
            id = "g1_6",
            level = "HSK 1",
            title = "Đại từ nghi vấn 什么, 谁, 哪儿",
            titleEn = "Interrogative pronouns 什么, 谁, 哪儿",
            formula = "Chủ ngữ + Động từ + Đại từ nghi vấn？",
            formulaEn = "Subject + Verb + Question Word?",
            explanation = "Trong tiếng Trung, đại từ nghi vấn đứng ở đúng vị trí của thành phần muốn hỏi mà không đảo ngữ.",
            explanationEn = "In Chinese, question words replace the unknown component without inverting word order.",
            examples = listOf(
                GrammarExample(
                    hanzi = "你想吃什么？你去哪儿？",
                    pinyin = "Nǐ xiǎng chī shénme? Nǐ qù nǎr?",
                    meaning = "Bạn muốn ăn cái gì? Bạn đi đâu thế?",
                    meaningEn = "What do you want to eat? Where are you going?"
                ),
                GrammarExample(
                    hanzi = "请问谁是王经理？",
                    pinyin = "Qǐngwèn shéi shì wáng jīnglǐ?",
                    meaning = "Xin hỏi ai là Giám đốc Vương?",
                    meaningEn = "Excuse me, who is Manager Wang?"
                ),
                GrammarExample(
                    hanzi = "洗手间在哪儿？",
                    pinyin = "Xǐshǒujiān zài nǎr?",
                    meaning = "Nhà vệ sinh ở đâu vậy?",
                    meaningEn = "Where is the restroom?"
                )
            ),
            exampleHanzi = "你想吃什么？你去哪儿？",
            examplePinyin = "Nǐ xiǎng chī shénme? Nǐ qù nǎr?",
            exampleMeaning = "Bạn muốn ăn cái gì? Bạn đi đâu thế?",
            exampleMeaningEn = "What do you want to eat? Where are you going?"
        ),
        GrammarTopic(
            id = "g2_1",
            level = "HSK 2",
            title = "Cấu trúc câu so sánh 比 (bǐ)",
            titleEn = "Comparative structure with 比 (bǐ)",
            formula = "A + 比 + B + Tính từ (+ Số lượng/得多)",
            formulaEn = "A + 比 + B + Adjective (+ Quantity/得多)",
            explanation = "Dùng để so sánh hơn giữa hai đối tượng A và B. Muốn nhấn mạnh mức độ dùng '得多' hoặc '一点儿' ở cuối câu.",
            explanationEn = "Used to compare A and B (A is more ... than B). Use '得多' or '一点儿' at the end for degree.",
            examples = listOf(
                GrammarExample(
                    hanzi = "哥哥比我高，今天比昨天冷得多。",
                    pinyin = "Gēge bǐ wǒ gāo, jīntiān bǐ zuótiān lěng de duō.",
                    meaning = "Anh trai cao hơn tôi, hôm nay lạnh hơn hôm qua nhiều.",
                    meaningEn = "My older brother is taller than me, today is much colder than yesterday."
                ),
                GrammarExample(
                    hanzi = "坐飞机比坐火车快多了。",
                    pinyin = "Zuò fēijī bǐ zuò huǒchē kuài duō le.",
                    meaning = "Đi máy bay nhanh hơn đi tàu hỏa rất nhiều.",
                    meaningEn = "Taking an airplane is much faster than taking a train."
                ),
                GrammarExample(
                    hanzi = "这个西瓜比那个甜一点儿。",
                    pinyin = "Zhè gè xīguā bǐ nà ge tián yìdiǎnr.",
                    meaning = "Quả dưa hấu này ngọt hơn quả kia một chút.",
                    meaningEn = "This watermelon is a bit sweeter than that one."
                )
            ),
            exampleHanzi = "哥哥比我高，今天比昨天冷得多。",
            examplePinyin = "Gēge bǐ wǒ gāo, jīntiān bǐ zuótiān lěng de duō.",
            exampleMeaning = "Anh trai cao hơn tôi, hôm nay lạnh hơn hôm qua nhiều.",
            exampleMeaningEn = "My older brother is taller than me, today is much colder than yesterday."
        ),
        GrammarTopic(
            id = "g2_2",
            level = "HSK 2",
            title = "Phó từ mức độ 很, 太...了, 非常",
            titleEn = "Degree adverbs 很, 太...了, 非常",
            formula = "太 + Tính từ + 了  |  非常 + Tính từ",
            formulaEn = "太 + Adjective + 了  |  非常 + Adjective",
            explanation = "'太...了' biểu thị cảm thán hoặc mức độ vượt mức; '非常' biểu thị mức độ vô cùng, đặc biệt.",
            explanationEn = "'太...了' conveys exclamation or excessive degree; '非常' means extraordinarily or exceptionally.",
            examples = listOf(
                GrammarExample(
                    hanzi = "这件衣服太漂亮了，我非常喜欢。",
                    pinyin = "Zhè jiàn yīfu tài piàoliang le, wǒ fēicháng xǐhuan.",
                    meaning = "Bộ quần áo này đẹp quá, tôi vô cùng thích.",
                    meaningEn = "This clothes is so beautiful, I like it very much."
                ),
                GrammarExample(
                    hanzi = "这道中国菜味道太棒了！",
                    pinyin = "Zhè dào zhōngguó cài wèidào tài bàng le!",
                    meaning = "Món ăn Trung Quốc này hương vị tuyệt vời quá!",
                    meaningEn = "The taste of this Chinese dish is absolutely fantastic!"
                ),
                GrammarExample(
                    hanzi = "这里的风景非常美丽。",
                    pinyin = "Zhèlǐ de fēngjǐng fēicháng měilì.",
                    meaning = "Phong cảnh ở đây vô cùng tươi đẹp.",
                    meaningEn = "The scenery here is extraordinarily beautiful."
                )
            ),
            exampleHanzi = "这件衣服太漂亮了，我非常喜欢。",
            examplePinyin = "Zhè jiàn yīfu tài piàoliang le, wǒ fēicháng xǐhuan.",
            exampleMeaning = "Bộ quần áo này đẹp quá, tôi vô cùng thích.",
            exampleMeaningEn = "This clothes is so beautiful, I like it very much."
        ),
        GrammarTopic(
            id = "g2_3",
            level = "HSK 2",
            title = "Động từ năng nguyện 会, 能, 可以",
            titleEn = "Modal verbs 会, 能, 可以",
            formula = "Chủ ngữ + 会 / 能 / 可以 + Động từ",
            formulaEn = "Subject + 会 / 能 / 可以 + Verb",
            explanation = "'会' biểu thị khả năng do học tập mà có; '能' biểu thị năng lực tự nhiên; '可以' biểu thị sự cho phép.",
            explanationEn = "'会' indicates acquired skill through learning; '能' indicates natural capacity; '可以' indicates permission.",
            examples = listOf(
                GrammarExample(
                    hanzi = "我会说汉语，我可以坐这里吗？",
                    pinyin = "Wǒ huì shuō hànyǔ, wǒ kěyǐ zuò zhèlǐ ma?",
                    meaning = "Tôi biết nói tiếng Trung, tôi có thể ngồi ở đây không?",
                    meaningEn = "I can speak Chinese, may I sit here?"
                ),
                GrammarExample(
                    hanzi = "他感冒了，今天不能来上班。",
                    pinyin = "Tā gǎnmào le, jīntiān bù néng lái shàngbān.",
                    meaning = "Anh ấy bị cảm rồi, hôm nay không thể đến đi làm.",
                    meaningEn = "He caught a cold and cannot come to work today."
                ),
                GrammarExample(
                    hanzi = "你会游水和开汽车吗？",
                    pinyin = "Nǐ huì yóushuǐ hé kāi qìchē ma?",
                    meaning = "Bạn có biết bơi và lái ô tô không?",
                    meaningEn = "Do you know how to swim and drive a car?"
                )
            ),
            exampleHanzi = "我会说汉语，我可以坐这里吗？",
            examplePinyin = "Wǒ huì shuō hànyǔ, wǒ kěyǐ zuò zhèlǐ ma?",
            exampleMeaning = "Tôi biết nói tiếng Trung, tôi có thể ngồi ở đây không?",
            exampleMeaningEn = "I can speak Chinese, may I sit here?"
        ),
        GrammarTopic(
            id = "g2_4",
            level = "HSK 2",
            title = "Trợ từ động thái 了 (le) - Hành động hoàn thành",
            titleEn = "Dynamic particle 了 (le) - Completed action",
            formula = "Động từ + 了 (+ Tân ngữ)",
            formulaEn = "Verb + 了 (+ Object)",
            explanation = "Đứng ngay sau động từ để biểu thị động tác đã hoàn thành hoặc đã xảy ra.",
            explanationEn = "Placed right after a verb to signify that the action has been completed.",
            examples = listOf(
                GrammarExample(
                    hanzi = "我买了两个苹果，他已经回家了。",
                    pinyin = "Wǒ mǎi le liǎng gè píngguǒ, tā yǐjīng huíjiā le.",
                    meaning = "Tôi đã mua hai quả táo, anh ấy đã về nhà rồi.",
                    meaningEn = "I bought two apples, he has already gone home."
                ),
                GrammarExample(
                    hanzi = "我看完了这本小说。",
                    pinyin = "Wǒ kàn wán le zhè běn xiǎoshuō.",
                    meaning = "Tôi đã đọc xong cuốn tiểu thuyết này.",
                    meaningEn = "I have finished reading this novel."
                ),
                GrammarExample(
                    hanzi = "他喝了一杯牛奶就去睡觉了。",
                    pinyin = "Tā hē le yì bēi niúnǎi jiù qù shuìjiào le.",
                    meaning = "Anh ấy uống một ly sữa xong liền đi ngủ rồi.",
                    meaningEn = "He drank a glass of milk and went to sleep."
                )
            ),
            exampleHanzi = "我买了两个苹果，他已经回家了。",
            examplePinyin = "Wǒ mǎi le liǎng gè píngguǒ, tā yǐjīng huíjiā le.",
            exampleMeaning = "Tôi đã mua hai quả táo, anh ấy đã về nhà rồi.",
            exampleMeaningEn = "I bought two apples, he has already gone home."
        ),
        GrammarTopic(
            id = "g2_5",
            level = "HSK 2",
            title = "Cấu trúc thời gian ...的时候 (...de shíhou)",
            titleEn = "Time structure ...的时候 (...de shíhou) - When/While",
            formula = "Hành động / Sự kiện + 的时候",
            formulaEn = "Action / Event + 的时候",
            explanation = "Biểu thị mốc thời gian khi một hành vi, trạng thái khác đang diễn ra (khi... / lúc...).",
            explanationEn = "Specifies a point or duration in time when another action occurs (when... / while...).",
            examples = listOf(
                GrammarExample(
                    hanzi = "我吃饭的时候不喜欢看手机。",
                    pinyin = "Wǒ chīfàn de shíhou bù xǐhuan kàn shǒujī.",
                    meaning = "Lúc ăn cơm tôi không thích nhìn điện thoại.",
                    meaningEn = "When eating meal, I do not like looking at mobile phones."
                ),
                GrammarExample(
                    hanzi = "我小的时候常常跟朋友去游泳。",
                    pinyin = "Wǒ xiǎo de shíhou chángcháng gēn péngyou qù yóuyǒng.",
                    meaning = "Lúc tôi còn nhỏ thường cùng bạn bè đi bơi.",
                    meaningEn = "When I was young, I often went swimming with friends."
                ),
                GrammarExample(
                    hanzi = "过马路的时候要注意安全。",
                    pinyin = "Guò mǎlù de shíhou yào zhùyì ānquán.",
                    meaning = "Khi qua đường phải chú ý an toàn.",
                    meaningEn = "When crossing the road, pay attention to safety."
                )
            ),
            exampleHanzi = "我吃饭的时候不喜欢看手机。",
            examplePinyin = "Wǒ chīfàn de shíhou bù xǐhuan kàn shǒujī.",
            exampleMeaning = "Lúc ăn cơm tôi không thích nhìn điện thoại.",
            exampleMeaningEn = "When eating meal, I do not like looking at mobile phones."
        ),
        GrammarTopic(
            id = "g2_6",
            level = "HSK 2",
            title = "Cấu trúc phương vị 从...到... (cóng...dào...)",
            titleEn = "Spatial and temporal structure 从...到...",
            formula = "从 + Mốc A + 到 + Mốc B",
            formulaEn = "从 + Point A + 到 + Point B",
            explanation = "Biểu thị khoảng cách từ điểm xuất phát đến điểm kết thúc theo không gian hoặc thời gian.",
            explanationEn = "Denotes the span from a starting point to a destination in space or time (from... to...).",
            examples = listOf(
                GrammarExample(
                    hanzi = "从北京到上海坐高铁很快。",
                    pinyin = "Cóng běijīng dào shànghǎi zuò gāotiě hěn kuài.",
                    meaning = "Từ Bắc Kinh đến Thượng Hải đi tàu cao tốc rất nhanh.",
                    meaningEn = "Taking high-speed rail from Beijing to Shanghai is very fast."
                ),
                GrammarExample(
                    hanzi = "从星期一到星期五我都要去上班。",
                    pinyin = "Cóng xīngqīyī dào xīngqīwǔ wǒ dōu yào qù shàngbān.",
                    meaning = "Từ Thứ hai đến Thứ sáu tôi đều phải đi làm.",
                    meaningEn = "From Monday to Friday I have to go to work."
                ),
                GrammarExample(
                    hanzi = "从我家到学校只需要十分钟。",
                    pinyin = "Cóng wǒ jiā dào xuéxiào zhǐ xūyào shí fēnzhōng.",
                    meaning = "Từ nhà tôi đến trường chỉ cần mười phút.",
                    meaningEn = "It only takes ten minutes from my home to school."
                )
            ),
            exampleHanzi = "从北京到上海坐高铁很快。",
            examplePinyin = "Cóng běijīng dào shànghǎi zuò gāotiě hěn kuài.",
            exampleMeaning = "Từ Bắc Kinh đến Thượng Hải đi tàu cao tốc rất nhanh.",
            exampleMeaningEn = "Taking high-speed rail from Beijing to Shanghai is very fast."
        ),
        GrammarTopic(
            id = "g3_1",
            level = "HSK 3",
            title = "Cấu trúc câu chữ 把 (bǎ) - Câu xử trí",
            titleEn = "Disposal sentence with 把 (bǎ)",
            formula = "Chủ ngữ + 把 + Tân ngữ + Động từ + Thành phần khác",
            formulaEn = "Subject + 把 + Object + Verb + Other components",
            explanation = "Dùng khi người nói muốn tác động lên một tân ngữ xác định và tạo ra sự thay đổi hoặc kết quả.",
            explanationEn = "Used when the subject acts upon a definite object and brings about a specific result or relocation.",
            examples = listOf(
                GrammarExample(
                    hanzi = "请把书放在桌子上。",
                    pinyin = "Qǐng bǎ shū fàng zài zhuōzi shàng.",
                    meaning = "Xin hãy để quyển sách lên trên bàn.",
                    meaningEn = "Please place the book on the table."
                ),
                GrammarExample(
                    hanzi = "我已经把作业做完了。",
                    pinyin = "Wǒ yǐjīng bǎ zuòyè zuò wán le.",
                    meaning = "Tôi đã làm xong bài tập về nhà rồi.",
                    meaningEn = "I have already finished my homework."
                ),
                GrammarExample(
                    hanzi = "请把门关上，外面很冷。",
                    pinyin = "Qǐng bǎ mén guān shàng, wàimiàn hěn lěng.",
                    meaning = "Làm ơn đóng cửa lại, bên ngoài rất lạnh.",
                    meaningEn = "Please close the door, it is very cold outside."
                )
            ),
            exampleHanzi = "请把书放在桌子上。",
            examplePinyin = "Qǐng bǎ shū fàng zài zhuōzi shàng.",
            exampleMeaning = "Xin hãy để quyển sách lên trên bàn.",
            exampleMeaningEn = "Please place the book on the table."
        ),
        GrammarTopic(
            id = "g3_2",
            level = "HSK 3",
            title = "Cấu trúc câu chữ 被 (bèi) - Câu bị động",
            titleEn = "Passive voice sentence with 被 (bèi)",
            formula = "Chủ ngữ (Bị tác động) + 被 + Chủ thể tác động + Động từ + Thành phần khác",
            formulaEn = "Subject (Receiver) + 被 + Agent + Verb + Other components",
            explanation = "Biểu thị câu bị động (ai/cái gì bị tác động bởi ai), thường mang sắc thái không mong muốn hoặc trung tính.",
            explanationEn = "Marks the passive voice (subject is acted upon by an agent), often carrying an unfortunate nuance.",
            examples = listOf(
                GrammarExample(
                    hanzi = "我的手机被弟弟拿走了。",
                    pinyin = "Wǒ de shǒujī bèi dìdi ná zǒu le.",
                    meaning = "Điện thoại của tôi bị em trai lấy đi rồi.",
                    meaningEn = "My mobile phone was taken away by my younger brother."
                ),
                GrammarExample(
                    hanzi = "自行车被小偷偷走了。",
                    pinyin = "Zìxíngchē bèi xiǎotōu tōu zǒu le.",
                    meaning = "Chiếc xe đạp đã bị kẻ trộm lấy cắp mất rồi.",
                    meaningEn = "The bicycle was stolen by a thief."
                ),
                GrammarExample(
                    hanzi = "那杯咖啡被我不小心打翻了。",
                    pinyin = "Nà bēi kāfēi bèi wǒ bù xiǎoxīn dǎ fān le.",
                    meaning = "Ly cà phê đó đã bị tôi sơ ý làm đổ rồi.",
                    meaningEn = "That cup of coffee was accidentally knocked over by me."
                )
            ),
            exampleHanzi = "我的手机被弟弟拿走了。",
            examplePinyin = "Wǒ de shǒujī bèi dìdi ná zǒu le.",
            exampleMeaning = "Điện thoại của tôi bị em trai lấy đi rồi.",
            exampleMeaningEn = "My mobile phone was taken away by my younger brother."
        ),
        GrammarTopic(
            id = "g3_3",
            level = "HSK 3",
            title = "Cấu trúc 越...越... (yuè...yuè...) - Càng... càng...",
            titleEn = "Correlative structure 越...越... (The more... the more...)",
            formula = "越 + A + 越 + B",
            formulaEn = "越 + A + 越 + B (The more A, the more B)",
            explanation = "Biểu thị mức độ của B sẽ tăng tiến tương ứng theo sự biến đổi mức độ của A.",
            explanationEn = "Indicates that the degree of B changes proportionately with the progression of A.",
            examples = listOf(
                GrammarExample(
                    hanzi = "汉语越学越有意思。",
                    pinyin = "Hànyǔ yuè xué yuè yǒu yìsi.",
                    meaning = "Tiếng Trung càng học càng thấy thú vị.",
                    meaningEn = "The more I study Chinese, the more interesting it becomes."
                ),
                GrammarExample(
                    hanzi = "雨越下越大，路上的车越来越少。",
                    pinyin = "Yǔ yuè xià yuè dà, lùshang de chē yuèláiyuè shǎo.",
                    meaning = "Mưa càng lúc càng to, xe cộ trên đường càng ngày càng ít.",
                    meaningEn = "The rain is getting heavier, and cars on the street are becoming fewer."
                ),
                GrammarExample(
                    hanzi = "你越紧张，就越容易犯错。",
                    pinyin = "Nǐ yuè jǐnzhāng, jiù yuè róngyì fàncuò.",
                    meaning = "Bạn càng căng thẳng thì càng dễ phạm lỗi sai.",
                    meaningEn = "The more nervous you are, the easier it is to make mistakes."
                )
            ),
            exampleHanzi = "汉语越学越有意思。",
            examplePinyin = "Hànyǔ yuè xué yuè yǒu yìsi.",
            exampleMeaning = "Tiếng Trung càng học càng thấy thú vị.",
            exampleMeaningEn = "The more I study Chinese, the more interesting it becomes."
        ),
        GrammarTopic(
            id = "g3_4",
            level = "HSK 3",
            title = "Cấu trúc 虽然...但是... (suīrán...dànshì...)",
            titleEn = "Concession structure 虽然...但是... (Although... but...)",
            formula = "虽然 + Vế 1, 但是 + Vế 2",
            formulaEn = "虽然 + Clause 1, 但是 + Clause 2",
            explanation = "Liên từ biểu thị quan hệ nhượng bộ và chuyển ý tương phản giữa hai vế câu.",
            explanationEn = "Expresses concession and contrast between two clauses (Although... yet...).",
            examples = listOf(
                GrammarExample(
                    hanzi = "虽然外面很冷，但是房间里很暖和。",
                    pinyin = "Suīrán wàimiàn hěn lěng, dànshì fángjiān lǐ hěn nuǎnhuo.",
                    meaning = "Mặc dù bên ngoài rất lạnh, nhưng trong phòng rất ấm áp.",
                    meaningEn = "Although it is very cold outside, it is warm inside the room."
                ),
                GrammarExample(
                    hanzi = "虽然工作很辛苦，但他每天都很开朗。",
                    pinyin = "Suīrán gōngzuò hěn xīnkǔ, dàn tā měitiān dōu hěn kāilǎng.",
                    meaning = "Mặc dù công việc rất vất vả, nhưng anh ấy mỗi ngày đều rất lạc quan.",
                    meaningEn = "Although work is demanding, he remains cheerful every day."
                ),
                GrammarExample(
                    hanzi = "虽然这辆车很贵，但是质量非常好。",
                    pinyin = "Suīrán zhè liàng chē hěn guì, dànshì zhìliàng fēicháng hǎo.",
                    meaning = "Mặc dù chiếc xe này đắt, nhưng chất lượng vô cùng tốt.",
                    meaningEn = "Although this car is expensive, its quality is exceptional."
                )
            ),
            exampleHanzi = "虽然外面很冷，但是房间里很暖和。",
            examplePinyin = "Suīrán wàimiàn hěn lěng, dànshì fángjiān lǐ hěn nuǎnhuo.",
            exampleMeaning = "Mặc dù bên ngoài rất lạnh, nhưng trong phòng rất ấm áp.",
            exampleMeaningEn = "Although it is very cold outside, it is warm inside the room."
        ),
        GrammarTopic(
            id = "g3_5",
            level = "HSK 3",
            title = "Trợ từ kết cấu 得 (de) - Bổ ngữ trạng thái / trình độ",
            titleEn = "Structural particle 得 (de) - State / Degree complement",
            formula = "Động từ + 得 + Tính từ / Cụm miêu tả",
            formulaEn = "Verb + 得 + Adjective / Descriptive clause",
            explanation = "Dùng sau động từ để dẫn ra nhận xét, đánh giá về kết quả hoặc trình độ của hành động.",
            explanationEn = "Used after a verb to introduce assessment, manner, or degree of the completed action.",
            examples = listOf(
                GrammarExample(
                    hanzi = "他跑得很快，汉字写得非常漂亮。",
                    pinyin = "Tā pǎo de hěn kuài, hànzì xiě de fēicháng piàoliang.",
                    meaning = "Anh ấy chạy rất nhanh, chữ Hán viết vô cùng đẹp.",
                    meaningEn = "He runs very fast, and his Chinese characters are written beautifully."
                ),
                GrammarExample(
                    hanzi = "妹妹今天穿得特别漂亮。",
                    pinyin = "Mèimei jīntiān chuān de tèbié piàoliang.",
                    meaning = "Em gái hôm nay ăn mặc đặc biệt xinh đẹp.",
                    meaningEn = "Younger sister is dressed especially beautifully today."
                ),
                GrammarExample(
                    hanzi = "王老师汉语讲得非常流利生动。",
                    pinyin = "Wáng lǎoshī hànyǔ jiǎng de fēicháng liúlì shēngdòng.",
                    meaning = "Thầy Vương giảng tiếng Trung vô cùng lưu loát và sinh động.",
                    meaningEn = "Teacher Wang explains Chinese very fluently and vividly."
                )
            ),
            exampleHanzi = "他跑得很快，汉字写得非常漂亮。",
            examplePinyin = "Tā pǎo de hěn kuài, hànzì xiě de fēicháng piàoliang.",
            exampleMeaning = "Anh ấy chạy rất nhanh, chữ Hán viết vô cùng đẹp.",
            exampleMeaningEn = "He runs very fast, and his Chinese characters are written beautifully."
        ),
        GrammarTopic(
            id = "g3_6",
            level = "HSK 3",
            title = "Cấu trúc 一...就... (yī...jiù...) - Vừa... liền...",
            titleEn = "Immediate succession 一...就... (As soon as... then...)",
            formula = "Chủ ngữ + 一 + Hành động 1 + 就 + Hành động 2",
            formulaEn = "Subject + 一 + Action 1 + 就 + Action 2",
            explanation = "Biểu thị hành động thứ hai diễn ra ngay tức khắc ngay khi hành động thứ nhất vừa xảy ra.",
            explanationEn = "Indicates that the second action occurs immediately following the completion of the first.",
            examples = listOf(
                GrammarExample(
                    hanzi = "他一下课就回宿舍了。",
                    pinyin = "Tā yí xiàkè jiù huí sùshè le.",
                    meaning = "Anh ấy vừa tan học liền quay về ký túc xá rồi.",
                    meaningEn = "As soon as class was dismissed, he went back to the dormitory."
                ),
                GrammarExample(
                    hanzi = "我一喝咖啡就睡不着觉。",
                    pinyin = "Wǒ yì hē kāfēi jiù shuì bu zháo jiào.",
                    meaning = "Tôi hễ uống cà phê là liền không ngủ được.",
                    meaningEn = "As soon as I drink coffee, I cannot fall asleep."
                ),
                GrammarExample(
                    hanzi = "妈妈一回到家就开始做饭。",
                    pinyin = "Māma yì huí dào jiā jiù kāishǐ zuòfàn.",
                    meaning = "Mẹ vừa về đến nhà liền bắt đầu nấu cơm.",
                    meaningEn = "As soon as mom arrived home, she began cooking."
                )
            ),
            exampleHanzi = "他一下课就回宿舍了。",
            examplePinyin = "Tā yí xiàkè jiù huí sùshè le.",
            exampleMeaning = "Anh ấy vừa tan học liền quay về ký túc xá rồi.",
            exampleMeaningEn = "As soon as class was dismissed, he went back to the dormitory."
        ),
        GrammarTopic(
            id = "g4_1",
            level = "HSK 4",
            title = "Cấu trúc 不仅...而且... (bùjǐn...érqiě...)",
            titleEn = "Additive conjunction 不仅...而且... (Not only... but also...)",
            formula = "不仅 + Điều kiện A, 而且 + Điều kiện B",
            formulaEn = "不仅 + Condition A, 而且 + Condition B",
            explanation = "Biểu thị quan hệ tăng tiến nhiều tầng, không chỉ đạt được A mà còn phát triển thêm B.",
            explanationEn = "Expresses progressive addition, stating that beyond A, an additional B is also present.",
            examples = listOf(
                GrammarExample(
                    hanzi = "他不仅聪明，而且非常勤奋。",
                    pinyin = "Tā bùjǐn cōngmíng, érqiě fēicháng qínfèn.",
                    meaning = "Anh ấy không những thông minh, mà còn vô cùng chăm chỉ.",
                    meaningEn = "He is not only intelligent, but also exceptionally diligent."
                ),
                GrammarExample(
                    hanzi = "学中文不仅能开阔眼界，而且对未来工作很有帮助。",
                    pinyin = "Xué zhōngwén bùjǐn néng kāikuò yǎnjiè, érqiě duì wèilái gōngzuò hěn yǒu bāngzhù.",
                    meaning = "Học tiếng Trung không chỉ mở rộng tầm mắt, mà còn rất hữu ích cho công việc sau này.",
                    meaningEn = "Learning Chinese not only broadens horizons, but also greatly helps future careers."
                ),
                GrammarExample(
                    hanzi = "这家饭馆不仅菜品丰富，而且服务非常热情。",
                    pinyin = "Zhè jiā fànguǎn bùjǐn càipǐn fēngfù, érqiě fúwù fēicháng rèqíng.",
                    meaning = "Nhà hàng này không những món ăn phong phú, mà phục vụ còn rất chu đáo nhiệt tình.",
                    meaningEn = "This restaurant not only offers a rich menu, but also provides warm and hospitable service."
                )
            ),
            exampleHanzi = "他不仅聪明，而且非常勤奋。",
            examplePinyin = "Tā bùjǐn cōngmíng, érqiě fēicháng qínfèn.",
            exampleMeaning = "Anh ấy không những thông minh, mà còn vô cùng chăm chỉ.",
            exampleMeaningEn = "He is not only intelligent, but also exceptionally diligent."
        ),
        GrammarTopic(
            id = "g4_2",
            level = "HSK 4",
            title = "Cấu trúc 连...也/都... (lián...yě/dōu...)",
            titleEn = "Emphatic construction 连...也/都... (Even...)",
            formula = "连 + Thành phần cực đoan + 也 / 都 + Động từ / Phủ định",
            formulaEn = "连 + Extreme element + 也 / 都 + Verb / Negation",
            explanation = "Nhấn mạnh trường hợp cá biệt, cực đoan để làm nổi bật cho các trường hợp thông thường khác.",
            explanationEn = "Emphasizes an extreme example to highlight that other ordinary cases are taken for granted.",
            examples = listOf(
                GrammarExample(
                    hanzi = "这个问题太简单了，连小孩子都明白。",
                    pinyin = "Zhè gè wèntí tài jiǎndān le, lián xiǎoháizi dōu míngbai.",
                    meaning = "Câu hỏi này đơn giản quá, ngay cả trẻ con cũng hiểu được.",
                    meaningEn = "This question is so simple that even children can understand it."
                ),
                GrammarExample(
                    hanzi = "他太忙了，连吃午饭的时间都没有。",
                    pinyin = "Tā tài máng le, lián chī wǔfàn de shíjiān dōu méiyǒu.",
                    meaning = "Anh ấy bận rộn quá, đến cả thời gian ăn trưa cũng không có.",
                    meaningEn = "He is so busy that he doesn't even have time to eat lunch."
                ),
                GrammarExample(
                    hanzi = "生病之后，他连走路的力气都没有了。",
                    pinyin = "Shēngbìng zhīhòu, tā lián zǒulù de lìqi dōu méiyǒu le.",
                    meaning = "Sau khi ngã bệnh, anh ấy ngay cả sức đi bộ cũng chẳng còn.",
                    meaningEn = "After falling ill, he didn't even have the strength to walk."
                )
            ),
            exampleHanzi = "这个问题太简单了，连小孩子都明白。",
            examplePinyin = "Zhè gè wèntí tài jiǎndān le, lián xiǎoháizi dōu míngbai.",
            exampleMeaning = "Câu hỏi này đơn giản quá, ngay cả trẻ con cũng hiểu được.",
            exampleMeaningEn = "This question is so simple that even children can understand it."
        ),
        GrammarTopic(
            id = "g4_3",
            level = "HSK 4",
            title = "Bổ ngữ khả năng (Verb + 得/不 + Kết quả)",
            titleEn = "Potential complement (Verb + 得/不 + Result)",
            formula = "Động từ + 得 / 不 + 到 / 完 / 懂 / 见",
            formulaEn = "Verb + 得 / 不 + Result (到 / 完 / 懂 / 见)",
            explanation = "Biểu thị điều kiện khách quan hoặc chủ quan có cho phép thực hiện được hành động tới kết quả hay không.",
            explanationEn = "Indicates whether conditions allow an action to reach its potential outcome.",
            examples = listOf(
                GrammarExample(
                    hanzi = "作业太多了，我今天做不完。",
                    pinyin = "Zuòyè tài duō le, wǒ jīntiān zuò bu wán.",
                    meaning = "Bài tập nhiều quá, hôm nay tôi làm không xong được.",
                    meaningEn = "There is too much homework, I cannot finish it today."
                ),
                GrammarExample(
                    hanzi = "他说得太快了，我听不懂。",
                    pinyin = "Tā shuō de tài kuài le, wǒ tīng bu dǒng.",
                    meaning = "Anh ấy nói nhanh quá, tôi nghe không hiểu.",
                    meaningEn = "He speaks too fast, I cannot understand."
                ),
                GrammarExample(
                    hanzi = "你站得那么高，看得到前面的风景吗？",
                    pinyin = "Nǐ zhàn de nàme gāo, kàn de dào qiánmiàn de fēngjǐng ma?",
                    meaning = "Bạn đứng cao như thế, có nhìn thấy được phong cảnh phía trước không?",
                    meaningEn = "You are standing so high, can you see the scenery ahead?"
                )
            ),
            exampleHanzi = "作业太多了，我今天做不完。",
            examplePinyin = "Zuòyè tài duō le, wǒ jīntiān zuò bu wán.",
            exampleMeaning = "Bài tập nhiều quá, hôm nay tôi làm không xong được.",
            exampleMeaningEn = "There is too much homework, I cannot finish it today."
        ),
        GrammarTopic(
            id = "g4_4",
            level = "HSK 4",
            title = "Cấu trúc 即使...也... (jíshǐ...yě...)",
            titleEn = "Hypothetical concession 即使...也... (Even if... still...)",
            formula = "即使 + Tình huống giả thiết, Cũng / Vẫn + Kết quả",
            formulaEn = "即使 + Hypothetical condition, 也 / 还 + Result",
            explanation = "Biểu thị sự nhượng bộ giả định, cho dù có xảy ra tình huống cực đoan đi nữa thì kết quả vẫn không đổi.",
            explanationEn = "Hypothetical concession showing that even under extreme assumptions, the result remains unchanged.",
            examples = listOf(
                GrammarExample(
                    hanzi = "即使遇到很大困难，我们也要坚持下去。",
                    pinyin = "Jíshǐ yùdào hěn dà kùnnán, wǒmen yě yào jiānchí xiàqù.",
                    meaning = "Cho dù gặp phải khó khăn to lớn, chúng ta cũng phải kiên trì tới cùng.",
                    meaningEn = "Even if we encounter great difficulties, we must persevere."
                ),
                GrammarExample(
                    hanzi = "即使只有一个人支持，我也不会放弃梦想。",
                    pinyin = "Jíshǐ zhǐyǒu yí gè rén zhīchí, wǒ yě bú huì fàngqì mèngxiǎng.",
                    meaning = "Dù chỉ có một người ủng hộ, tôi cũng sẽ không từ bỏ ước mơ.",
                    meaningEn = "Even if only one person supports me, I will never abandon my dream."
                ),
                GrammarExample(
                    hanzi = "即使天气不好，比赛也会照常举行。",
                    pinyin = "Jíshǐ tiānqì bù hǎo, bǐsài yě huì zhàocháng jǔxíng.",
                    meaning = "Cho dù thời tiết xấu, cuộc thi vẫn sẽ diễn ra như thường lệ.",
                    meaningEn = "Even if the weather is bad, the match will proceed as scheduled."
                )
            ),
            exampleHanzi = "即使遇到很大困难，我们也要坚持下去。",
            examplePinyin = "Jíshǐ yùdào hěn dà kùnnán, wǒmen yě yào jiānchí xiàqù.",
            exampleMeaning = "Cho dù gặp phải khó khăn to lớn, chúng ta cũng phải kiên trì tới cùng.",
            exampleMeaningEn = "Even if we encounter great difficulties, we must persevere."
        ),
        GrammarTopic(
            id = "g4_5",
            level = "HSK 4",
            title = "Từ nối 无论...都... (wúlùn...dōu...)",
            titleEn = "Inclusive condition 无论...都... (No matter what...)",
            formula = "无论 + Phương án lựa chọn / Đại từ nghi vấn, 都 + Kết luận",
            formulaEn = "无论 + Alternatives / Question word, 都 + Conclusion",
            explanation = "Biểu thị trong bất kỳ hoàn cảnh hay điều kiện nào thì kết quả hay hành động đều giữ nguyên.",
            explanationEn = "Indicates that under any condition or alternative, the result remains constant.",
            examples = listOf(
                GrammarExample(
                    hanzi = "无论刮风下雨，他都按时来上课。",
                    pinyin = "Wúlùn guāfēng xiàyǔ, tā dōu ànshí lái shàngkè.",
                    meaning = "Bất kể gió giông hay mưa rơi, anh ấy đều đến lớp đúng giờ.",
                    meaningEn = "No matter if it is windy or raining, he arrives at class on time."
                ),
                GrammarExample(
                    hanzi = "无论发生什么事，我都会站在你身边。",
                    pinyin = "Wúlùn fāshēng shénme shì, wǒ dōu huì zhàn zài nǐ shēnbiān.",
                    meaning = "Bất kể xảy ra chuyện gì, tôi đều sẽ đứng về phía bạn.",
                    meaningEn = "No matter what happens, I will always stand by your side."
                ),
                GrammarExample(
                    hanzi = "无论工作多么忙碌，他每天都坚持锻炼身体。",
                    pinyin = "Wúlùn gōngzuò duōme mánglù, tā měitiān dōu jiānchí duànliàn shēntǐ.",
                    meaning = "Bất kể công việc bận rộn đến đâu, anh ấy mỗi ngày đều kiên trì rèn luyện thân thể.",
                    meaningEn = "No matter how busy work is, he insists on exercising every day."
                )
            ),
            exampleHanzi = "无论刮风下雨，他都按时来上课。",
            examplePinyin = "Wúlùn guāfēng xiàyǔ, tā dōu ànshí lái shàngkè.",
            exampleMeaning = "Bất kể gió giông hay mưa rơi, anh ấy đều đến lớp đúng giờ.",
            exampleMeaningEn = "No matter if it is windy or raining, he arrives at class on time."
        ),
        GrammarTopic(
            id = "g4_6",
            level = "HSK 4",
            title = "Cấu trúc 除了...以外，还/都... (chúle...yǐwài)",
            titleEn = "Exceptive / Additive 除了...以外 (Apart from / Besides)",
            formula = "除了 + A + 以外，还 / 都 + B",
            formulaEn = "除了 + A + 以外, 还 / 都 + B",
            explanation = "Có hai nghĩa: Trừ A ra tất cả đều B (dùng 都), hoặc ngoài A ra vẫn còn thêm B (dùng 还/也).",
            explanationEn = "Holds dual functions: exception (with 都 - except A, all are B) or addition (with 还 - besides A, also B).",
            examples = listOf(
                GrammarExample(
                    hanzi = "除了英语以外，他还会说法语和中文。",
                    pinyin = "Chúle yīngyǔ yǐwài, tā hái huì shuō fǎyǔ hé zhōngwén.",
                    meaning = "Ngoài tiếng Anh ra, anh ấy còn biết nói tiếng Pháp và tiếng Trung.",
                    meaningEn = "Besides English, he can also speak French and Chinese."
                ),
                GrammarExample(
                    hanzi = "除了小李以外，大家今天都按时到达了。",
                    pinyin = "Chúle xiǎo lǐ yǐwài, dàjiā jīntiān dōu ànshí dàodá le.",
                    meaning = "Ngoại trừ Tiểu Lý ra, hôm nay mọi người đều đã đến đúng giờ.",
                    meaningEn = "Except for Xiao Li, everyone arrived on time today."
                ),
                GrammarExample(
                    hanzi = "除了看书以外，听音乐也是我最大的爱好。",
                    pinyin = "Chúle kànshū yǐwài, tīng yīnyuè yě shì wǒ zuì dà de àihào.",
                    meaning = "Bên cạnh việc đọc sách, nghe nhạc cũng là sở thích lớn nhất của tôi.",
                    meaningEn = "Besides reading, listening to music is also my greatest hobby."
                )
            ),
            exampleHanzi = "除了英语以外，他还会说法语和中文。",
            examplePinyin = "Chúle yīngyǔ yǐwài, tā hái huì shuō fǎyǔ hé zhōngwén.",
            exampleMeaning = "Ngoài tiếng Anh ra, anh ấy còn biết nói tiếng Pháp và tiếng Trung.",
            exampleMeaningEn = "Besides English, he can also speak French and Chinese."
        ),
        GrammarTopic(
            id = "g5_1",
            level = "HSK 5",
            title = "Cấu trúc 据...报道/统计 (jù...bàodào/tǒngjì)",
            titleEn = "Attribution structure 据... (According to reports/statistics)",
            formula = "据 + Nguồn trích dẫn / Số liệu + Động từ/Danh từ",
            formulaEn = "据 + Cited Source / Statistics + Verb / Noun",
            explanation = "Dùng trong văn viết hoặc báo chí trang trọng để trích dẫn căn cứ, nguồn thông tin xác thực.",
            explanationEn = "Used in formal journalism or professional writing to cite authentic sources or data.",
            examples = listOf(
                GrammarExample(
                    hanzi = "据统计，今年来华留学生人数大幅增加。",
                    pinyin = "Jù tǒngjì, jīnnián lái huá liúxuéshēng rénshù dàfú zēngjiā.",
                    meaning = "Theo số liệu thống kê, số lượng du học sinh đến Trung Quốc năm nay đã tăng mạnh.",
                    meaningEn = "According to statistics, the number of international students coming to China increased substantially this year."
                ),
                GrammarExample(
                    hanzi = "据专家预测，未来五年人工智能将迎来更大突破。",
                    pinyin = "Jù zhuānjiā yùcè, wèilái wǔ nián réngōng zhìnéng jiāng yínglái gèng dà tūpò.",
                    meaning = "Theo dự đoán của các chuyên gia, trí tuệ nhân tạo sẽ đón nhận đột phá lớn hơn trong 5 năm tới.",
                    meaningEn = "According to expert forecasts, artificial intelligence will achieve greater breakthroughs in the next five years."
                ),
                GrammarExample(
                    hanzi = "据最新调查显示，绿色健康生活方式受到更多年轻人的青睐。",
                    pinyin = "Jù zuìxīn diàochá xiǎnshì, lǜsè jiànkāng shēnghuó fāngshì shòudào gèng duō niánqīngrén de qīnglài.",
                    meaning = "Theo khảo sát mới nhất cho thấy, lối sống xanh và lành mạnh đang được nhiều bạn trẻ ưa chuộng hơn.",
                    meaningEn = "According to the latest survey, green and healthy lifestyles are favored by more young people."
                )
            ),
            exampleHanzi = "据统计，今年来华留学生人数大幅增加。",
            examplePinyin = "Jù tǒngjì, jīnnián lái huá liúxuéshēng rénshù dàfú zēngjiā.",
            exampleMeaning = "Theo số liệu thống kê, số lượng du học sinh đến Trung Quốc năm nay đã tăng mạnh.",
            exampleMeaningEn = "According to statistics, the number of international students coming to China increased substantially this year."
        ),
        GrammarTopic(
            id = "g5_2",
            level = "HSK 5",
            title = "Cấu trúc 宁可...也不... (nìngkě...yě bù...)",
            titleEn = "Deliberate choice 宁可...也不... (Would rather... than...)",
            formula = "宁可 + Chấp nhận cái xấu ít hơn, 也不 + Chấp nhận cái xấu hơn",
            formulaEn = "宁可 + Lesser sacrifice, 也不 + Unacceptable outcome",
            explanation = "Biểu thị sự lựa chọn có tính toán: thà chịu một thiệt thòi có hạn chứ nhất quyết không chịu hậu quả tồi tệ.",
            explanationEn = "Expresses a resolute choice between two unpleasant options (preferring sacrifice over worse outcomes).",
            examples = listOf(
                GrammarExample(
                    hanzi = "他宁可自己多辛苦，也不愿意麻烦别人。",
                    pinyin = "Tā nìngkě zìjǐ duō xīnkǔ, yě bú yuànyì máfan biérén.",
                    meaning = "Anh ấy thà bản thân chịu vất vả hơn, chứ không muốn làm phiền người khác.",
                    meaningEn = "He would rather endure hardship himself than trouble others."
                ),
                GrammarExample(
                    hanzi = "为了保证产品质量，他们宁可延期交货，也不偷工减料。",
                    pinyin = "Wèile bǎozhèng chǎnpǐn zhìliàng, tāmen nìngkě yánqī jiāohuò, yě bù tōugōng jiǎnliào.",
                    meaning = "Để đảm bảo chất lượng sản phẩm, họ thà giao hàng trễ chứ tuyệt đối không làm ẩu bớt xén.",
                    meaningEn = "To guarantee product quality, they would rather delay delivery than cut corners."
                ),
                GrammarExample(
                    hanzi = "我宁可步行走回家，也不想挤这么拥挤的公交车。",
                    pinyin = "Wǒ nìngkě bùxíng zǒu huí jiā, yě bù xiǎng jǐ zhème yōngjǐ de gōngjiāochē.",
                    meaning = "Tôi thà đi bộ về nhà, chứ không muốn chen chúc trên chuyến xe buýt đông đúc thế này.",
                    meaningEn = "I would rather walk home than squeeze into such a crowded bus."
                )
            ),
            exampleHanzi = "他宁可自己多辛苦，也不愿意麻烦别人。",
            examplePinyin = "Tā nìngkě zìjǐ duō xīnkǔ, yě bú yuànyì máfan biérén.",
            exampleMeaning = "Anh ấy thà bản thân chịu vất vả hơn, chứ không muốn làm phiền người khác.",
            exampleMeaningEn = "He would rather endure hardship himself than trouble others."
        ),
        GrammarTopic(
            id = "g5_3",
            level = "HSK 5",
            title = "Cấu trúc 凡是...都... (fánshì...dōu...)",
            titleEn = "Universal quantifier 凡是...都... (All / Every)",
            formula = "凡是 + Điều kiện chung, 都 + Kết luận",
            formulaEn = "凡是 + General condition, 都 + Conclusion",
            explanation = "Mang sắc thái trang trọng, khẳng định tất cả các cá thể thuộc phạm vi điều kiện đều có chung tính chất.",
            explanationEn = "Formal universal expression asserting that every member within the specified scope shares the attribute.",
            examples = listOf(
                GrammarExample(
                    hanzi = "凡是参加比赛的人，都能获得一份精美纪念品。",
                    pinyin = "Fánshì cānjiā bǐsài de rén, dōu néng huòdé yí fèn jīngměi jìniànpǐn.",
                    meaning = "Phàm là người tham gia thi đấu, đều có thể nhận được một phần quà lưu niệm tinh xảo.",
                    meaningEn = "Whoever participates in the competition can receive an exquisite souvenir."
                ),
                GrammarExample(
                    hanzi = "凡是有利于环境保护的事情，我们都要积极去做。",
                    pinyin = "Fánshì yǒulì yú huánjìng bǎohù de shìqing, wǒmen dōu yào jījí qù zuò.",
                    meaning = "Hễ là việc gì có lợi cho bảo vệ môi trường, chúng ta đều phải tích cực thực hiện.",
                    meaningEn = "Whatever benefits environmental protection, we must proactively do."
                ),
                GrammarExample(
                    hanzi = "凡是认真努力的员工，公司都会给予公平的晋升机会。",
                    pinyin = "Fánshì rènzhēn nǔlì de yuángōng, gōngsī dōu huì jǐyǔ gōngpíng de jìnshēng jīhuì.",
                    meaning = "Phàm là nhân viên chăm chỉ nỗ lực, công ty đều sẽ trao cơ hội thăng tiến công bằng.",
                    meaningEn = "All employees who work conscientiously will be granted fair promotion opportunities by the company."
                )
            ),
            exampleHanzi = "凡是参加比赛的人，都能获得一份精美纪念品。",
            examplePinyin = "Fánshì cānjiā bǐsài de rén, dōu néng huòdé yí fèn jīngměi jìniànpǐn.",
            exampleMeaning = "Phàm là người tham gia thi đấu, đều có thể nhận được một phần quà lưu niệm tinh xảo.",
            exampleMeaningEn = "Whoever participates in the competition can receive an exquisite souvenir."
        ),
        GrammarTopic(
            id = "g5_4",
            level = "HSK 5",
            title = "Phó từ chuyển ý 倒 (dào) - Ngược lại / Trái lại",
            titleEn = "Concessive adverb 倒 (dào) - On the contrary / Unexpectedly",
            formula = "Chủ ngữ + 倒 + Động từ / Tính từ",
            formulaEn = "Subject + 倒 + Verb / Adjective",
            explanation = "Dùng để biểu thị sự việc trái ngược với lẽ thường, hoặc dùng khi muốn nói giảm nói tránh, nhượng bộ.",
            explanationEn = "Used to convey an unexpected reversal contrary to anticipation, or polite concession.",
            examples = listOf(
                GrammarExample(
                    hanzi = "这道题看起来很难，做起来倒挺容易。",
                    pinyin = "Zhè dào tí kàn qǐlái hěn nán, zuò qǐlái dào tǐng róngyì.",
                    meaning = "Bài toán này nhìn có vẻ rất khó, nhưng khi làm lại khá dễ.",
                    meaningEn = "This problem looks difficult, but doing it turns out to be quite easy."
                ),
                GrammarExample(
                    hanzi = "虽然屋子不大，收拾得倒十分整洁温馨。",
                    pinyin = "Suīrán wūzi bú dà, shōushi de dào shífēn zhěngjié wēnxīn.",
                    meaning = "Tuy căn phòng không lớn, nhưng được thu dọn lại rất ngăn nắp và ấm cúng.",
                    meaningEn = "Although the room is not large, it is arranged very neatly and cozily."
                ),
                GrammarExample(
                    hanzi = "大家都觉得很累，他倒精神饱满。",
                    pinyin = "Dàjiā dōu juéde hěn lèi, tā dào jīngshén bǎomǎn.",
                    meaning = "Mọi người đều cảm thấy rất mệt, riêng anh ấy ngược lại vẫn tràn đầy tinh thần.",
                    meaningEn = "Everyone felt exhausted, while he was unexpectedly full of energy."
                )
            ),
            exampleHanzi = "这道题看起来很难，做起来倒挺容易。",
            examplePinyin = "Zhè dào tí kàn qǐlái hěn nán, zuò qǐlái dào tǐng róngyì.",
            exampleMeaning = "Bài toán này nhìn có vẻ rất khó, nhưng khi làm lại khá dễ.",
            exampleMeaningEn = "This problem looks difficult, but doing it turns out to be quite easy."
        ),
        GrammarTopic(
            id = "g5_5",
            level = "HSK 5",
            title = "Cấu trúc 以便 (yǐbiàn) / 以免 (yǐmiǎn)",
            titleEn = "Purpose connectors 以便 (In order to) / 以免 (Lest / So as not to)",
            formula = "Hành động chuẩn bị, 以便 / 以免 + Mục đích / Rủi ro",
            formulaEn = "Preparatory action, 以便 / 以免 + Purpose / Risk",
            explanation = "'以便' dùng để nối tiếp mục đích tạo thuận lợi; '以免' dùng để dự phòng tránh sự cố không mong muốn.",
            explanationEn = "'以便' introduces purpose to facilitate follow-ups; '以免' expresses caution to prevent undesired events.",
            examples = listOf(
                GrammarExample(
                    hanzi = "请提前出发，以免路上堵车耽误行程。",
                    pinyin = "Qǐng tíqián chūfā, yǐmiǎn lùshang dǔchē dānwu xíngchéng.",
                    meaning = "Xin hãy xuất phát sớm, để tránh kẹt xe trên đường làm lỡ lịch trình.",
                    meaningEn = "Please depart early, so as to avoid traffic jams delaying the itinerary."
                ),
                GrammarExample(
                    hanzi = "请大家留下联系电话，以便后续通知面试结果。",
                    pinyin = "Qǐng dàjiā liú xià liánxì diànhuà, yǐbiàn hòuxù tōngzhī miànshì jiéguǒ.",
                    meaning = "Xin mọi người hãy để lại số điện thoại liên lạc, để tiện cho việc thông báo kết quả phỏng vấn sau này.",
                    meaningEn = "Please leave your contact numbers so as to facilitate subsequent notification of interview results."
                ),
                GrammarExample(
                    hanzi = "出门前务必检查门窗，以免雨水淋湿室内。",
                    pinyin = "Chūmén qián wùbì jiǎnchá ménchuāng, yǐmiǎn yǔshuǐ línshī shìnèi.",
                    meaning = "Trước khi ra khỏi nhà nhớ kiểm tra cửa nẻo, nhằm tránh nước mưa tạt ướt trong phòng.",
                    meaningEn = "Be sure to check windows and doors before leaving to prevent rain from soaking the room."
                )
            ),
            exampleHanzi = "请提前出发，以免路上堵车耽误行程。",
            examplePinyin = "Qǐng tíqián chūfā, yǐmiǎn lùshang dǔchē dānwu xíngchéng.",
            exampleMeaning = "Xin hãy xuất phát sớm, để tránh kẹt xe trên đường làm lỡ lịch trình.",
            exampleMeaningEn = "Please depart early, so as to avoid traffic jams delaying the itinerary."
        ),
        GrammarTopic(
            id = "g5_6",
            level = "HSK 5",
            title = "Cấu trúc 所谓...就是... (suǒwèi...jiù shì...)",
            titleEn = "Definitional structure 所谓...就是... (The so-called... means...)",
            formula = "所谓 + Khái niệm / Danh xưng, 就是 + Giải thích bản chất",
            formulaEn = "所谓 + Concept / Term, 就是 + Essential definition",
            explanation = "Dùng trong luận văn hoặc diễn giải để định nghĩa bản chất một thuật ngữ hay hiện tượng xã hội.",
            explanationEn = "Used in expository prose to define the fundamental essence of a concept or phenomenon.",
            examples = listOf(
                GrammarExample(
                    hanzi = "所谓的成功，就是每天坚持超越昨天的自己。",
                    pinyin = "Suǒwèi de chénggōng, jiù shì měitiān jiānchí chāoyuè zuótiān de zìjǐ.",
                    meaning = "Cái gọi là thành công, chính là mỗi ngày kiên trì vượt qua bản thân của ngày hôm qua.",
                    meaningEn = "The so-called success is persevering every day to surpass who you were yesterday."
                ),
                GrammarExample(
                    hanzi = "所谓的团队精神，就是大家为了共同的目标携手拼搏。",
                    pinyin = "Suǒwèi de tuánduì jīngshén, jiù shì dàjiā wèile gòngtóng de mùbiāo xiéshǒu pīnbó.",
                    meaning = "Cái gọi là tinh thần đồng đội, chính là mọi người vì mục tiêu chung mà sát cánh nỗ lực.",
                    meaningEn = "The so-called team spirit means everyone joining hands to strive for a common goal."
                ),
                GrammarExample(
                    hanzi = "所谓的成熟，就是在面对挫折时依然保持冷静与理智。",
                    pinyin = "Suǒwèi de chéngshú, jiù shì zài miànduì cuòzhé shí yīrán bǎochí lěngjìng yǔ lǐzhì.",
                    meaning = "Cái gọi là trưởng thành, chính là khi đối diện trắc trở vẫn duy trì sự bình tĩnh và lý trí.",
                    meaningEn = "The so-called maturity is maintaining calm and rationality even when facing setbacks."
                )
            ),
            exampleHanzi = "所谓的成功，就是每天坚持超越昨天的自己。",
            examplePinyin = "Suǒwèi de chénggōng, jiù shì měitiān jiānchí chāoyuè zuótiān de zìjǐ.",
            exampleMeaning = "Cái gọi là thành công, chính là mỗi ngày kiên trì vượt qua bản thân của ngày hôm qua.",
            exampleMeaningEn = "The so-called success is persevering every day to surpass who you were yesterday."
        ),
        GrammarTopic(
            id = "g6_1",
            level = "HSK 6",
            title = "Cấu trúc 鉴于... (jiànyú...) - Xét thấy, căn cứ vào",
            titleEn = "Formal justification 鉴于... (In view of / In light of)",
            formula = "鉴于 + Hoàn cảnh / Lý do thực tế, Mệnh đề chính",
            formulaEn = "鉴于 + Context / Grounds, Main Clause",
            explanation = "Thường dùng trong công văn, báo cáo học thuật để nêu căn cứ dẫn tới quyết sách, điều chỉnh quan trọng.",
            explanationEn = "Used in official documents and academic discourses to cite formal grounds justifying decisions.",
            examples = listOf(
                GrammarExample(
                    hanzi = "鉴于当前气候变化严峻，各国必须携手采取行动。",
                    pinyin = "Jiànyú dāngqián qìhòu biànhuà yánjùn, gèguó bìxū xiéshǒu cǎiqǔ xíngdòng.",
                    meaning = "Xét thấy biến đổi khí hậu hiện nay rất nghiêm trọng, các quốc gia bắt buộc phải chung tay hành động.",
                    meaningEn = "In view of the severe climate change at present, all countries must join hands to take action."
                ),
                GrammarExample(
                    hanzi = "鉴于原材料价格持续上涨，公司决定适度调整产品售价。",
                    pinyin = "Jiànyú yuáncáiliào jiàgé chíxù shàngzhǎng, gōngsī juédìng shìdù tiáozhěng chǎnpǐn shòujià.",
                    meaning = "Xét thấy giá nguyên vật liệu liên tục tăng cao, công ty quyết định điều chỉnh vừa phải giá bán sản phẩm.",
                    meaningEn = "In light of ongoing raw material price hikes, the company decided to adjust product selling prices appropriately."
                ),
                GrammarExample(
                    hanzi = "鉴于小李在项目中表现杰出，管理层一致同意提拔他为部门主管。",
                    pinyin = "Jiànyú xiǎo lǐ zài xiàngmù zhōng biǎoxiàn jiéchū, guǎnlǐcéng yízhì tóngyì tíbá tā wéi bùmén zhǔguǎn.",
                    meaning = "Xét thấy Tiểu Lý có biểu hiện xuất sắc trong dự án, ban quản lý nhất trí đồng ý đề bạt anh làm trưởng phòng.",
                    meaningEn = "In view of Xiao Li's outstanding performance in the project, management unanimously agreed to promote him to department head."
                )
            ),
            exampleHanzi = "鉴于当前气候变化严峻，各国必须携手采取行动。",
            examplePinyin = "Jiànyú dāngqián qìhòu biànhuà yánjùn, gèguó bìxū xiéshǒu cǎiqǔ xíngdòng.",
            exampleMeaning = "Xét thấy biến đổi khí hậu hiện nay rất nghiêm trọng, các quốc gia bắt buộc phải chung tay hành động.",
            exampleMeaningEn = "In view of the severe climate change at present, all countries must join hands to take action."
        ),
        GrammarTopic(
            id = "g6_2",
            level = "HSK 6",
            title = "Cấu trúc 无非是 / 不外乎 (wúfēishì / bùwàihū)",
            titleEn = "Exclusivity phrasing 无非是 / 不外乎 (Nothing more than / Merely)",
            formula = "Chủ ngữ + 无非是 / 不外乎 + Nguyên nhân / Phạm trù",
            formulaEn = "Subject + 无非是 / 不外乎 + Core cause / Category",
            explanation = "Dùng để đúc kết hoặc quy nạp vấn đề về bản chất cốt lõi, mang nghĩa 'chẳng qua chỉ là', 'không ngoài...'.",
            explanationEn = "Reduces complexities to their fundamental core, meaning 'nothing other than' or 'merely'.",
            examples = listOf(
                GrammarExample(
                    hanzi = "他之所以如此努力，无非是想为家人创造更好的生活。",
                    pinyin = "Tā zhī suǒyǐ rúcǐ nǔlì, wúfēishì xiǎng wèi jiārén chuàngzào gèng hǎo de shēnghuó.",
                    meaning = "Sở dĩ anh ấy nỗ lực như vậy, chẳng qua chỉ là muốn tạo dựng cuộc sống tốt hơn cho gia đình.",
                    meaningEn = "The reason he strives so diligently is nothing more than wanting to create a better life for his family."
                ),
                GrammarExample(
                    hanzi = "现代企业竞争的根本，不外乎人才与创新两个要素。",
                    pinyin = "Xiàndài qǐyè jìngzhēng de gēnběn, búwàihū réncái yǔ chuàngxīn liǎng gè yàosù.",
                    meaning = "Gốc rễ của sự cạnh tranh giữa các doanh nghiệp hiện đại, không ngoài hai yếu tố nhân tài và sáng tạo đổi mới.",
                    meaningEn = "The root of modern corporate competition is nothing other than two factors: talent and innovation."
                ),
                GrammarExample(
                    hanzi = "大家争论了半天，意见的分歧无非是关于实施的时间节点。",
                    pinyin = "Dàjiā zhēnglùn le bàntiān, yìjiàn de fēnqí wúfēishì guānyú shíshī de shíjiān jiédiǎn.",
                    meaning = "Mọi người tranh luận hồi lâu, sự bất đồng ý kiến chẳng qua chỉ là về mốc thời gian triển khai.",
                    meaningEn = "After debating for a long time, the divergence of opinions is merely concerning the implementation timeline."
                )
            ),
            exampleHanzi = "他之所以如此努力，无非是想为家人创造更好的生活。",
            examplePinyin = "Tā zhī suǒyǐ rúcǐ nǔlì, wúfēishì xiǎng wèi jiārén chuàngzào gèng hǎo de shēnghuó.",
            exampleMeaning = "Sở dĩ anh ấy nỗ lực như vậy, chẳng qua chỉ là muốn tạo dựng cuộc sống tốt hơn cho gia đình.",
            exampleMeaningEn = "The reason he strives so diligently is nothing more than wanting to create a better life for his family."
        ),
        GrammarTopic(
            id = "g6_3",
            level = "HSK 6",
            title = "Cấu trúc 姑且不说...单就...而言 (gūqiě bù shuō...)",
            titleEn = "Comparative isolation 姑且不说...单就...而言 (Leaving aside... judging solely by...)",
            formula = "姑且不说 + Yếu tố A, 单就 + Yếu tố B + 而言",
            formulaEn = "姑且不说 + Element A, 单就 + Element B + 而言",
            explanation = "Tạm gác lại một mặt hiển nhiên hoặc rộng lớn hơn, chỉ tập trung phân tích một khía cạnh cụ thể cũng đủ thuyết phục.",
            explanationEn = "Sets aside broader arguments to demonstrate that focusing solely on one facet is already conclusive.",
            examples = listOf(
                GrammarExample(
                    hanzi = "姑且不说投资成本，单就环境效益而言这项方案就值得推广。",
                    pinyin = "Gūqiě bù shuō tóuzī chéngběn, dān jiù huánjìng xiàoyì éryán zhè xiàng fāng'àn jiù zhíde tuīguǎng.",
                    meaning = "Tạm thời chưa bàn tới chi phí đầu tư, chỉ riêng xét về mặt hiệu quả môi trường thì phương án này đã rất đáng nhân rộng.",
                    meaningEn = "Leaving aside investment costs, judging solely by environmental benefits, this proposal is well worth promoting."
                ),
                GrammarExample(
                    hanzi = "姑且不说剧情如何精彩，单就演员精湛的演技而言这部电影就不可错过。",
                    pinyin = "Gūqiě bù shuō jùqíng rúhé jīngcǎi, dān jiù yǎnyuán jīngzhàn de yǎnjì éryán zhè bù diànyǐng jiù bùkě cuòguò.",
                    meaning = "Tạm thời chưa bàn kịch bản hấp dẫn ra sao, chỉ riêng diễn xuất điêu luyện của diễn viên đã khiến bộ phim không thể bỏ lỡ.",
                    meaningEn = "Leaving aside the engaging plot, judging solely by the actors' superb acting, this movie is not to be missed."
                ),
                GrammarExample(
                    hanzi = "姑且不说长远战略，单就眼前收益而言这项合作也是双赢之举。",
                    pinyin = "Gūqiě bù shuō chángyuǎn zhànlüè, dān jiù yǎnqián shòuyì éryán zhè xiàng hézuò yě shì shuāngyíng zhī jǔ.",
                    meaning = "Tạm chưa nói về chiến lược lâu dài, chỉ riêng lợi ích trước mắt thì sự hợp tác này đã là nước cờ đôi bên cùng thắng.",
                    meaningEn = "Leaving aside long-term strategy, judging solely by immediate returns, this collaboration is already a win-win."
                )
            ),
            exampleHanzi = "姑且不说投资成本，单就环境效益而言这项方案就值得推广。",
            examplePinyin = "Gūqiě bù shuō tóuzī chéngběn, dān jiù huánjìng xiàoyì éryán zhè xiàng fāng'àn jiù zhíde tuīguǎng.",
            exampleMeaning = "Tạm thời chưa bàn tới chi phí đầu tư, chỉ riêng xét về mặt hiệu quả môi trường thì phương án này đã rất đáng nhân rộng.",
            exampleMeaningEn = "Leaving aside investment costs, judging solely by environmental benefits, this proposal is well worth promoting."
        ),
        GrammarTopic(
            id = "g6_4",
            level = "HSK 6",
            title = "Cấu trúc 哪怕...也/依然... (nǎpà...yě/yīrán...)",
            titleEn = "Extreme hypothetical concession 哪怕...也... (Even if / No matter how...)",
            formula = "哪怕 + Tình thế gian truân nhất, Cũng / Vẫn + Giữ vững ý chí",
            formulaEn = "哪怕 + Hardest adversity, 也 / 依然 + Steadfast action",
            explanation = "Biểu thị sự kiên định không lay chuyển trước bất kỳ nghịch cảnh khắc nghiệt nào trong văn học và hùng biện.",
            explanationEn = "Signifies unwavering determination even in the face of the most extreme adversity in rhetoric.",
            examples = listOf(
                GrammarExample(
                    hanzi = "哪怕前方道路充满未知，我们依然会坚定前行。",
                    pinyin = "Nǎpà qiánfāng dàolù chōngmǎn wèizhī, wǒmen yīrán huì jiāndìng qiánxíng.",
                    meaning = "Dẫu cho con đường phía trước tràn ngập điều chưa biết, chúng ta vẫn sẽ kiên định bước tiếp.",
                    meaningEn = "Even if the road ahead is full of uncertainties, we will still march forward firmly."
                ),
                GrammarExample(
                    hanzi = "哪怕只有百分之一的希望，医生们也会竭尽全力挽救生命。",
                    pinyin = "Nǎpà zhǐyǒu bǎifēnzhīyī de xīwàng, yīshēngmen yě huì jiéjìn quánlì wǎnjiù shēngmìng.",
                    meaning = "Dẫu cho chỉ còn một phần trăm hy vọng, các bác sĩ cũng sẽ dốc hết sức mình cứu lấy sinh mạng.",
                    meaningEn = "Even if there is only a one percent chance of hope, doctors will spare no effort to save lives."
                ),
                GrammarExample(
                    hanzi = "哪怕经历千辛万苦，他也誓要实现儿时的崇高理想。",
                    pinyin = "Nǎpà jīnglì qiānxīn wànkǔ, tā yě shì yào shíxiàn érshí de chónggāo lǐxiǎng.",
                    meaning = "Dẫu cho trải qua muôn vàn gian truân, anh ấy cũng thề sẽ hiện thực hóa lý tưởng cao đẹp thuở thiếu thời.",
                    meaningEn = "Even after enduring countless hardships, he vowed to realize the lofty ideals of his youth."
                )
            ),
            exampleHanzi = "哪怕前方道路充满未知，我们依然会坚定前行。",
            examplePinyin = "Nǎpà qiánfāng dàolù chōngmǎn wèizhī, wǒmen yīrán huì jiāndìng qiánxíng.",
            exampleMeaning = "Dẫu cho con đường phía trước tràn ngập điều chưa biết, chúng ta vẫn sẽ kiên định bước tiếp.",
            exampleMeaningEn = "Even if the road ahead is full of uncertainties, we will still march forward firmly."
        ),
        GrammarTopic(
            id = "g6_5",
            level = "HSK 6",
            title = "Cấu trúc 藉此 (jiè cǐ) / 借以 (jiè yǐ) - Nhân dịp này",
            titleEn = "Formal instrumental 藉此 / 借以 (Thereby / By means of this)",
            formula = "Biện pháp cụ thể, 藉此 / 借以 + Mục tiêu cao hơn",
            formulaEn = "Specific action, 藉此 / 借以 + Higher objective",
            explanation = "Phương thức từ trong văn phong hàn lâm biểu thị việc tận dụng cơ hội, đòn bẩy để hoàn thành mục tiêu chiến lược.",
            explanationEn = "Formal scholarly connector indicating leveraging an opportunity as a catalyst to accomplish higher objectives.",
            examples = listOf(
                GrammarExample(
                    hanzi = "我们举办文化交流节，借以增进两国青年的相互了解。",
                    pinyin = "Wǒmen jǔbàn wénhuà jiāoliú jié, jièyǐ zēngjìn liǎng guó qīngnián de xiānghù liǎojiě.",
                    meaning = "Chúng tôi tổ chức ngày hội giao lưu văn hóa, nhằm nhân dịp này nâng cao sự hiểu biết lẫn nhau giữa thanh niên hai nước.",
                    meaningEn = "We organize the cultural exchange festival, thereby enhancing mutual understanding between the youth of both nations."
                ),
                GrammarExample(
                    hanzi = "高校设立青年科研基金，藉此激发学生的科研创新潜能。",
                    pinyin = "Gāoxiào shèlì qīngnián kēyán jījīn, jiècǐ jīfā xuésheng de kēyán chuàngxīn qiánnéng.",
                    meaning = "Các trường đại học thành lập quỹ nghiên cứu khoa học trẻ, lấy đó để khơi dậy tiềm năng sáng tạo của sinh viên.",
                    meaningEn = "Universities establish youth research funds, thereby stimulating students' potential for scientific innovation."
                ),
                GrammarExample(
                    hanzi = "企业发布年度可持续发展报告，借以树立良好的社会责任形象。",
                    pinyin = "Qǐyè fābù niándù kěchíxù fāzhǎn bàogào, jièyǐ shùlì liánghǎo de shèhuì zérèn xíngxiàng.",
                    meaning = "Doanh nghiệp công bố báo cáo phát triển bền vững thường niên, nhân đó xây dựng hình ảnh trách nhiệm xã hội tốt đẹp.",
                    meaningEn = "The enterprise published its annual sustainability report, thereby establishing a favorable corporate responsibility image."
                )
            ),
            exampleHanzi = "我们举办文化交流节，借以增进两国青年的相互了解。",
            examplePinyin = "Wǒmen jǔbàn wénhuà jiāoliú jié, jièyǐ zēngjìn liǎng guó qīngnián de xiānghù liǎojiě.",
            exampleMeaning = "Chúng tôi tổ chức ngày hội giao lưu văn hóa, nhằm nhân dịp này nâng cao sự hiểu biết lẫn nhau giữa thanh niên hai nước.",
            exampleMeaningEn = "We organize the cultural exchange festival, thereby enhancing mutual understanding between the youth of both nations."
        ),
        GrammarTopic(
            id = "g6_6",
            level = "HSK 6",
            title = "Cấu trúc 势必 (shìbì) & 毋庸置疑 (wúyōng zhìyí)",
            titleEn = "Inevitable assertion 势必 (Inevitably) & 毋庸置疑 (Undoubtedly)",
            formula = "Chủ thể + 势必 + Hệ quả tất yếu  |  毋庸置疑，Mệnh đề",
            formulaEn = "Subject + 势必 + Inevitable outcome  |  毋庸置疑, Clause",
            explanation = "Từ ngữ học thuật khẳng định xu thế tất yếu không thể đảo ngược hoặc sự thật hiển nhiên không còn nghi ngờ gì nữa.",
            explanationEn = "Scholarly assertions stating an irreversible inevitable trend or an indisputable fact.",
            examples = listOf(
                GrammarExample(
                    hanzi = "科技的飞速发展，势必深刻改变人类未来的生活方式。",
                    pinyin = "Kējì de fēisù fāzhǎn, shìbì shēnkè gǎibiàn rénlèi wèilái de shēnghuó fāngshì.",
                    meaning = "Sự phát triển vũ bão của khoa học công nghệ, tất yếu sẽ thay đổi sâu sắc phương thức sống của nhân loại trong tương lai.",
                    meaningEn = "The rapid advancement of science and technology will inevitably transform humanity's future way of life profoundly."
                ),
                GrammarExample(
                    hanzi = "毋庸置疑，教育投入是一个国家繁荣昌盛的基石。",
                    pinyin = "Wúyōng zhìyí, jiàoyù tóurù shì yí gè guójiā fánróng chāngshèng de jīshí.",
                    meaning = "Không còn nghi ngờ gì nữa, đầu tư cho giáo dục chính là nền tảng vững chắc cho sự phồn vinh thịnh vượng của một quốc gia.",
                    meaningEn = "Undoubtedly, investment in education is the bedrock of a nation's prosperity."
                ),
                GrammarExample(
                    hanzi = "如果忽视生态环境保护，势必给子孙后代带来无法挽回的灾难。",
                    pinyin = "Rúguǒ hūshì shēngtài huánjìng bǎohù, shìbì gěi zǐsūn hòudài dàilái wúfǎ wǎnhuí de zāinàn.",
                    meaning = "Nếu xem nhẹ việc bảo vệ môi trường sinh thái, tất yếu sẽ mang lại thảm họa khôn lường cho thế hệ con cháu mai sau.",
                    meaningEn = "Neglecting ecological protection will inevitably inflict irreversible disasters upon future generations."
                )
            ),
            exampleHanzi = "科技的飞速发展，势必深刻改变人类未来的生活方式。",
            examplePinyin = "Kējì de fēisù fāzhǎn, shìbì shēnkè gǎibiàn rénlèi wèilái de shēnghuó fāngshì.",
            exampleMeaning = "Sự phát triển vũ bão của khoa học công nghệ, tất yếu sẽ thay đổi sâu sắc phương thức sống của nhân loại trong tương lai.",
            exampleMeaningEn = "The rapid advancement of science and technology will inevitably transform humanity's future way of life profoundly."
        )
    )

    val sampleCategories = listOf(
        SentenceCategory(
            id = "c1",
            title = "Chào hỏi & Xã giao",
            titleEn = "Greetings & Social Etiquette",
            iconEmoji = "🤝",
            description = "Mẫu câu mở đầu hội thoại, chào hỏi lịch thiệp, tạm biệt",
            descriptionEn = "Polite opening greetings, everyday social etiquette, and farewells",
            sentences = listOf(
                SentenceItem("s1_1", "很高兴认识你！", "Hěn gāoxìng rènshí nǐ!", "Rất vui được làm quen với bạn!", "Very glad to meet you!"),
                SentenceItem("s1_2", "好久不见，最近怎么样？", "Hǎojiǔ bú jiàn, zuìjìn zěnmeyàng?", "Lâu rồi không gặp, dạo này bạn thế nào?", "Long time no see, how have you been lately?"),
                SentenceItem("s1_3", "祝你今天过得愉快！", "Zhù nǐ jīntiān guò de yúkuài!", "Chúc bạn một ngày vui vẻ!", "Have a wonderful day today!"),
                SentenceItem("s1_4", "慢走，有空常来玩！", "Màn zǒu, yǒukòng cháng lái wán!", "Đi cẩn thận nhé, rảnh rỗi nhớ thường xuyên ghé chơi!", "Take care, visit us whenever you are free!"),
                SentenceItem("s1_5", "早上好，昨晚睡得好吗？", "Zǎoshang hǎo, zuówǎn shuì de hǎo ma?", "Chào buổi sáng, tối qua bạn ngủ ngon không?", "Good morning, did you sleep well last night?"),
                SentenceItem("s1_6", "请问您贵姓？", "Qǐngwèn nín guìxìng?", "Xin hỏi quý danh của ngài là gì?", "May I ask your honorable surname?"),
                SentenceItem("s1_7", "初次见面，请多关照。", "Chūcì jiànmiàn, qǐng duō guānzhào.", "Lần đầu gặp mặt, xin được chỉ giáo nhiều hơn.", "Nice to meet you, please treat me well."),
                SentenceItem("s1_8", "路上辛苦了，快请进！", "Lùshang xīnkǔ le, kuài qǐng jìn!", "Đi đường vất vả rồi, mau mời vào nhà!", "You had a long journey, please come in!"),
                SentenceItem("s1_9", "改天有空我们一起聚聚。", "Gǎitiān yǒukòng wǒmen yìqǐ jùju.", "Hôm nào rảnh chúng mình cùng tụ tập nhé.", "Let's get together sometime when you are free."),
                SentenceItem("s1_10", "保持联系，随时给我发微信。", "Bǎochí liánxì, suíshí gěi wǒ fā wēixìn.", "Giữ liên lạc nhé, cứ nhắn WeChat cho tôi bất cứ lúc nào.", "Keep in touch, feel free to WeChat me anytime.")
            )
        ),
        SentenceCategory(
            id = "c2",
            title = "Ăn uống & Nhà hàng",
            titleEn = "Dining & Restaurant",
            iconEmoji = "🍜",
            description = "Gọi món ăn, hỏi hương vị, yêu cầu khẩu vị, thanh toán",
            descriptionEn = "Ordering food, asking for flavors, dietary requests, and settling bills",
            sentences = listOf(
                SentenceItem("s2_1", "服务员，请问有什么特色菜？", "Fúwùyuán, qǐngwèn yǒu shénme tèsè cài?", "Phục vụ ơi, cho hỏi quán có món đặc sản gì?", "Waiter, what are the house specialties here?"),
                SentenceItem("s2_2", "请不要放辣，谢谢。", "Qǐng búyào fàng là, xièxie.", "Làm ơn đừng cho cay nhé, cảm ơn.", "Please make it non-spicy, thank you."),
                SentenceItem("s2_3", "买单，可以用微信支付吗？", "Mǎidān, kěyǐ yòng wēixìn zhīfù ma?", "Tính tiền, có thể trả bằng WeChat Pay không?", "Check please, can I pay using WeChat Pay?"),
                SentenceItem("s2_4", "请给我们一份菜单。", "Qǐng gěi wǒmen yí fèn càidān.", "Làm ơn cho chúng tôi một cuốn thực đơn.", "Please give us a menu."),
                SentenceItem("s2_5", "这个菜太好吃了，味道很地道！", "Zhè gè cài tài hǎochī le, wèidào hěn dìdao!", "Món này ngon quá, hương vị rất chuẩn mực!", "This dish is delicious, the flavor is very authentic!"),
                SentenceItem("s2_6", "麻烦再加一碗米饭。", "Máfan zài jiā yì wǎn mǐfàn.", "Phiền bạn cho thêm một bát cơm trắng nữa.", "Could you bring another bowl of rice please?"),
                SentenceItem("s2_7", "我吃素，这道菜里面有肉吗？", "Wǒ chīsù, zhè dào cài lǐmiàn yǒu ròu ma?", "Tôi ăn chay, trong món này có thịt không?", "I am vegetarian, does this dish contain meat?"),
                SentenceItem("s2_8", "请问洗手间在哪里？", "Qǐngwèn xǐshǒujiān zài nǎlǐ?", "Xin hỏi nhà vệ sinh ở đâu vậy?", "Excuse me, where is the restroom?"),
                SentenceItem("s2_9", "请帮我们打包剩下的菜。", "Qǐng bāng wǒmen dǎbāo shèngxià de cài.", "Làm ơn gói giúp chúng tôi phần thức ăn còn thừa.", "Please pack the leftovers for us to take away."),
                SentenceItem("s2_10", "我要一杯温开水，谢谢。", "Wǒ yào yì bēi wēn kāishuǐ, xièxie.", "Tôi muốn một ly nước ấm, cảm ơn.", "I would like a glass of warm water, thank you.")
            )
        ),
        SentenceCategory(
            id = "c3",
            title = "Du lịch & Khách sạn",
            titleEn = "Travel & Accommodation",
            iconEmoji = "✈️",
            description = "Đặt phòng khách sạn, làm thủ tục nhận phòng, du lịch ngắm cảnh",
            descriptionEn = "Hotel booking, check-in procedures, sightseeing, and tours",
            sentences = listOf(
                SentenceItem("s3_1", "我想预订一间大床房。", "Wǒ xiǎng yùdìng yì jiān dàchuáng fáng.", "Tôi muốn đặt một phòng giường đôi lớn.", "I would like to book a king-size bed room."),
                SentenceItem("s3_2", "请问几点可以办理入住手续？", "Qǐngwèn jǐ diǎn kěyǐ bànlǐ rùzhù shǒuxù?", "Xin hỏi mấy giờ có thể làm thủ tục nhận phòng?", "What time can I check into the hotel?"),
                SentenceItem("s3_3", "请问房费包含早餐吗？", "Qǐngwèn fángfèi bāohán zǎocān ma?", "Xin hỏi tiền phòng có bao gồm bữa sáng không?", "Does the room rate include breakfast?"),
                SentenceItem("s3_4", "房间里的无线网络密码是多少？", "Fángjiān lǐ de wúxiàn wǎngluò mìmǎ shì duōshao?", "Mật khẩu Wi-Fi trong phòng là bao nhiêu?", "What is the Wi-Fi password for the room?"),
                SentenceItem("s3_5", "我要退房，请帮我叫一辆出租车。", "Wǒ yào tuìfáng, qǐng bāng wǒ jiào yí liàng chūzūchē.", "Tôi muốn trả phòng, làm ơn gọi giúp tôi một chiếc taxi.", "I would like to check out, please call a taxi for me."),
                SentenceItem("s3_6", "请问附近有什么著名的旅游景点？", "Qǐngwèn fùjìn yǒu shénme zhùmíng de lǚyóu jǐngdiǎn?", "Xin hỏi gần đây có điểm du lịch nổi tiếng nào không?", "Are there any famous tourist attractions nearby?"),
                SentenceItem("s3_7", "景区的门票多少钱一张？", "Jǐngqū de ménpiào duōshao qián yì zhāng?", "Vé vào cổng khu danh lam bao nhiêu tiền một vé?", "How much is an entrance ticket to the scenic spot?"),
                SentenceItem("s3_8", "能帮我们在景点前面拍张合影吗？", "Néng bāng wǒmen zài jǐngdiǎn qiánmiàn pāi zhāng héyǐng ma?", "Bạn có thể chụp giúp chúng tôi một bức ảnh kỷ niệm trước danh lam không?", "Could you help take a group photo for us in front of the scenic spot?"),
                SentenceItem("s3_9", "请问行李可以免费寄存吗？", "Qǐngwèn xíngli kěyǐ miǎnfèi jìcún ma?", "Xin hỏi hành lý có được gửi miễn phí không?", "Can I store my luggage here free of charge?"),
                SentenceItem("s3_10", "这里的夜景真是太震撼了！", "Zhèlǐ de yèjǐng zhēnshì tài zhènhàn le!", "Cảnh đêm ở đây quả thật quá đỗi choáng ngợp!", "The night view here is truly breathtaking!")
            )
        ),
        SentenceCategory(
            id = "c4",
            title = "Mua sắm & Mặc cả",
            titleEn = "Shopping & Bargaining",
            iconEmoji = "🛍️",
            description = "Hỏi giá cả, thử quần áo, chọn màu sắc kích cỡ, thương lượng giá",
            descriptionEn = "Inquiring prices, trying on clothes, sizes and colors, bargaining discounts",
            sentences = listOf(
                SentenceItem("s4_1", "这件衣服多少钱？", "Zhè jiàn yīfu duōshao qián?", "Bộ quần áo này giá bao nhiêu tiền?", "How much does this piece of clothing cost?"),
                SentenceItem("s4_2", "太贵了，能不能便宜一点儿？", "Tài guì le, néng bu néng piányi yìdiǎnr?", "Đắt quá, có thể bớt một chút được không?", "It is too expensive, could you make it a bit cheaper?"),
                SentenceItem("s4_3", "我可以试穿一下这件外套吗？", "Wǒ kěyǐ shìchuān yíxià zhè jiàn wàitào ma?", "Tôi có thể mặc thử chiếc áo khoác này không?", "May I try on this coat?"),
                SentenceItem("s4_4", "请问有大一点的尺码吗？", "Qǐngwèn yǒu dà yìdiǎn de chǐmǎ ma?", "Xin hỏi có kích cỡ lớn hơn một chút không?", "Do you have a slightly larger size?"),
                SentenceItem("s4_5", "这个款式还有其他颜色吗？", "Zhè gè kuǎnshì hái yǒu qítā yánsè ma?", "Kiểu dáng này còn màu sắc nào khác không?", "Does this style come in other colors?"),
                SentenceItem("s4_6", "如果买两件，可以打折吗？", "Rúguǒ mǎi liǎng jiàn, kěyǐ dǎzhé ma?", "Nếu mua hai chiếc thì có được giảm giá không?", "If I buy two pieces, can I get a discount?"),
                SentenceItem("s4_7", "质量很好，我就要这件了。", "Zhìliàng hěn hǎo, wǒ jiù yào zhè jiàn le.", "Chất lượng rất tốt, tôi lấy chiếc này nhé.", "The quality is very good, I will take this one."),
                SentenceItem("s4_8", "你们支持支付宝或刷卡吗？", "Nǐmen zhīchí zhīfùbǎo huò shuākǎ ma?", "Bên bạn có hỗ trợ Alipay hoặc quẹt thẻ không?", "Do you accept Alipay or credit cards?"),
                SentenceItem("s4_9", "请问可以开一张发票吗？", "Qǐngwèn kěyǐ kāi yì zhāng fāpiào ma?", "Xin hỏi có thể xuất cho tôi một hóa đơn không?", "Could you issue an official invoice for me?"),
                SentenceItem("s4_10", "如果不合适，七天内可以退换吗？", "Rúguǒ bù héshì, qī tiān nèi kěyǐ tuìhuàn ma?", "Nếu không vừa vặn, trong 7 ngày có được đổi trả không?", "If it does not fit, can I exchange or return it within seven days?")
            )
        ),
        SentenceCategory(
            id = "c5",
            title = "Hỏi đường & Giao thông",
            titleEn = "Directions & Transportation",
            iconEmoji = "🚕",
            description = "Đi tàu điện ngầm, xe buýt, taxi, định vị phương hướng",
            descriptionEn = "Taking subways, buses, taxis, asking directions and navigation",
            sentences = listOf(
                SentenceItem("s5_1", "请问去地铁站怎么走？", "Qǐngwèn qù dìtiězhàn zěnme zǒu?", "Xin hỏi đi đến ga tàu điện ngầm thế nào?", "Excuse me, how do I get to the subway station?"),
                SentenceItem("s5_2", "师傅，请打表，去高铁站。", "Shīfu, qǐng dǎbiǎo, qù gāotiě zhàn.", "Bác tài xế ơi, làm ơn bấm đồng hồ tính tiền, đến ga tàu cao tốc nhé.", "Driver, please turn on the meter, heading to the high-speed rail station."),
                SentenceItem("s5_3", "一直往前面走，然后在十字路口向右转。", "Yìzhí wǎng qiánmiàn zǒu, ránhòu zài shízì lùkǒu xiàng yòu zhuǎn.", "Cứ đi thẳng về phía trước, sau đó ở ngã tư thì rẽ phải.", "Walk straight ahead, then turn right at the crossroads."),
                SentenceItem("s5_4", "坐几路公交车能到市中心？", "Zuò jǐ lù gōngjiāochē néng dào shì zhōngxīn?", "Đi tuyến xe buýt số mấy thì đến được trung tâm thành phố?", "Which bus route goes to the city center?"),
                SentenceItem("s5_5", "去机场大概需要坐多长时间？", "Qù jīchǎng dàgài xūyào zuò duōcháng shíjiān?", "Đi đến sân bay ước chừng mất bao nhiêu thời gian?", "About how long does it take to reach the airport?"),
                SentenceItem("s5_6", "请问这一站是终点站吗？", "Qǐngwèn zhè yí zhàn shì zhōngdiǎnzhàn ma?", "Xin hỏi trạm này có phải là trạm cuối không?", "Is this station the final destination?"),
                SentenceItem("s5_7", "我好像迷路了，可以在地图上帮我指一下吗？", "Wǒ hǎoxiàng mílù le, kěyǐ zài dìtú shàng bāng wǒ zhǐ yíxià ma?", "Tôi hình như bị lạc đường rồi, bạn có thể chỉ giúp trên bản đồ không?", "I think I am lost, could you point it out for me on the map?"),
                SentenceItem("s5_8", "这列火车几点发车？", "Zhè liè huǒchē jǐ diǎn fāchē?", "Chuyến tàu hỏa này mấy giờ khởi hành?", "What time does this train depart?"),
                SentenceItem("s5_9", "请问去哪里可以购买地铁交通卡？", "Qǐngwèn qù nǎlǐ kěyǐ gòumǎi dìtiě jiāotōng kǎ?", "Xin hỏi đến đâu thì có thể mua thẻ giao thông tàu điện ngầm?", "Where can I purchase a subway transit card?"),
                SentenceItem("s5_10", "师傅，我就在前面路口下车。", "Shīfu, wǒ jiù zài qiánmiàn lùkǒu xiàchē.", "Bác tài ơi, tôi xuống xe ngay tại ngã rẽ phía trước nhé.", "Driver, please let me off at the intersection ahead.")
            )
        ),
        SentenceCategory(
            id = "c6",
            title = "Công việc & Văn phòng",
            titleEn = "Work & Office Communication",
            iconEmoji = "💼",
            description = "Giao tiếp công sở, họp hành, báo cáo tiến độ, hợp tác kinh doanh",
            descriptionEn = "Workplace interactions, meetings, reporting progress, and business cooperation",
            sentences = listOf(
                SentenceItem("s6_1", "今天下午两点我们有一个部门会议。", "Jīntiān xiàwǔ liǎng diǎn wǒmen yǒu yí gè bùmén huìyì.", "Chiều nay hai giờ chúng ta có một cuộc họp phòng ban.", "We have a departmental meeting at two o'clock this afternoon."),
                SentenceItem("s6_2", "我已经把最新的项目报告发到您的邮箱了。", "Wǒ yǐjīng bǎ zuìxīn de xiàngmù bàogào fā dào nín de yóuxiāng le.", "Tôi đã gửi bản báo cáo dự án mới nhất vào hòm thư của bạn rồi.", "I have already sent the latest project report to your email."),
                SentenceItem("s6_3", "这个方案的截止日期是本周五。", "Zhè gè fāng'àn de jiézhǐ rìqī shì běn zhōuwǔ.", "Hạn chót của phương án này là thứ Sáu tuần này.", "The deadline for this proposal is this Friday."),
                SentenceItem("s6_4", "期待我们未来有更深入的商业合作。", "Qīdài wǒmen wèilái yǒu gèng shēnrù de shāngyè hézuò.", "Rất mong đợi chúng ta sẽ có sự hợp tác kinh doanh sâu rộng hơn trong tương lai.", "Looking forward to deeper business cooperation with you in the future."),
                SentenceItem("s6_5", "请大家准时参加明天的业务培训。", "Qǐng dàjiā zhǔnshí cānjiā míngtiān de yèwù péixùn.", "Xin mọi người tham gia đúng giờ buổi đào tạo nghiệp vụ ngày mai.", "Please attend tomorrow's professional training punctually."),
                SentenceItem("s6_6", "请李经理审核并签署这份合作合同。", "Qǐng lǐ jīnglǐ shěnhé bìng qiānshǔ zhè fèn hézuò hétong.", "Kính mời Giám đốc Lý duyệt và ký kết hợp đồng hợp tác này.", "Please Manager Li review and sign this cooperation agreement."),
                SentenceItem("s6_7", "目前整个项目的进展非常顺利。", "Mùqián zhěng gè xiàngmù de jìnzhǎn fēicháng shùnlì.", "Hiện tại tiến độ của toàn bộ dự án diễn ra vô cùng thuận lợi.", "Currently the entire project progress is going very smoothly."),
                SentenceItem("s6_8", "如果有任何疑问，请随时跟我沟通。", "Rúguǒ yǒu rènhé yíwèn, qǐng suíshí gēn wǒ gōutōng.", "Nếu có bất kỳ thắc mắc nào, hãy thoải mái trao đổi với tôi.", "If you have any questions, please feel free to communicate with me."),
                SentenceItem("s6_9", "今天需要加班赶完这批紧急任务。", "Jīntiān xūyào jiābān gǎn wán zhè pī jǐnjí rènwu.", "Hôm nay cần phải tăng ca để hoàn thành cho kịp lô nhiệm vụ khẩn cấp này.", "We need to work overtime today to rush through these urgent tasks."),
                SentenceItem("s6_10", "感谢各位同事这段时间的辛勤付出！", "Gǎnxiè gèwèi tóngshì zhè duàn shíjiān de xīnqín fùchū!", "Chân thành cảm ơn các đồng nghiệp vì sự cống hiến chăm chỉ thời gian qua!", "Thank you colleagues for your hard work and dedication over this period!")
            )
        ),
        SentenceCategory(
            id = "c7",
            title = "Học tập & Trường lớp",
            titleEn = "School & Academic Life",
            iconEmoji = "📚",
            description = "Thảo luận bài tập, trao đổi trên lớp, kỳ thi, mượn sách thư viện",
            descriptionEn = "Discussing coursework, classroom interactions, exams, and libraries",
            sentences = listOf(
                SentenceItem("s7_1", "老师，请问这个生词是什么意思？", "Lǎoshī, qǐngwèn zhè gè shēngcí shì shénme yìsi?", "Thưa thầy, cho em hỏi từ mới này có nghĩa là gì ạ?", "Teacher, what does this new vocabulary word mean?"),
                SentenceItem("s7_2", "下周我们要进行汉语期末考试。", "Xià zhōu wǒmen yào jìnxíng hànyǔ qīmò kǎoshì.", "Tuần sau chúng ta sẽ bước vào kỳ thi học kỳ tiếng Trung.", "Next week we will take our Chinese final examination."),
                SentenceItem("s7_3", "今天下午放学后我们去图书馆自习吧。", "Jīntiān xiàwǔ fàngxué hòu wǒmen qù túshūguǎn zìxí ba.", "Chiều nay sau khi tan học chúng mình cùng đến thư viện tự học nhé.", "Let's go to the library to study after class this afternoon."),
                SentenceItem("s7_4", "多听录音对提高中文听力非常有帮助。", "Duō tīng lùyīn duì tígāo zhōngwén tīnglì fēicháng yǒu bāngzhù.", "Nghe nhiều ghi âm rất hữu ích cho việc nâng cao khả năng nghe tiếng Trung.", "Listening to recordings frequently greatly improves Chinese listening skills."),
                SentenceItem("s7_5", "请大家把课本翻到第七十五页。", "Qǐng dàjiā bǎ kèběn fān dào dì qīshíwǔ yè.", "Xin mời mọi người mở sách giáo khoa ra trang bảy mươi lăm.", "Please turn your textbook to page seventy-five."),
                SentenceItem("s7_6", "你能帮我检查一下这篇作文的语法吗？", "Nǐ néng bāng wǒ jiǎnchá yíxià zhè piān zuòwén de yǔfǎ ma?", "Bạn có thể kiểm tra giúp tôi ngữ pháp của bài tập làm văn này không?", "Could you help check the grammar of this essay for me?"),
                SentenceItem("s7_7", "我打算明年报名参加新HSK五级考试。", "Wǒ dǎsuàn míngnián bàomíng cānjiā xīn HSK wǔ jí kǎoshì.", "Tôi dự định sang năm sẽ đăng ký tham dự kỳ thi HSK 5 mới.", "I plan to register for the new HSK Level 5 exam next year."),
                SentenceItem("s7_8", "熟能生巧，每天坚持练习汉字就会写得很好。", "Shú néng shēng qiǎo, měitiān jiānchí liànxí hànzì jiù huì xiě de hěn hǎo.", "Trăm hay không bằng tay quen, mỗi ngày kiên trì luyện viết chữ Hán sẽ viết rất đẹp.", "Practice makes perfect; practicing Chinese characters daily will lead to great writing."),
                SentenceItem("s7_9", "请问这本中文参考书可以借阅多久？", "Qǐngwèn zhè běn zhōngwén cānkǎoshū kěyǐ jièyuè duōjiǔ?", "Xin hỏi cuốn sách tham khảo tiếng Trung này được mượn trong bao lâu?", "How long can I borrow this Chinese reference book for?"),
                SentenceItem("s7_10", "祝大家在这次考试中都能取得优异成绩！", "Zhù dàjiā zài zhè cì kǎoshì zhōng dōu néng qǔdé yōuyì chéngjì!", "Chúc tất cả các bạn đều đạt được thành tích xuất sắc trong kỳ thi lần này!", "Wishing everyone outstanding results in this examination!")
            )
        ),
        SentenceCategory(
            id = "c8",
            title = "Sức khỏe & Bệnh viện",
            titleEn = "Health & Medical Care",
            iconEmoji = "🏥",
            description = "Miêu tả triệu chứng, khám bệnh, mua thuốc, chăm sóc sức khỏe",
            descriptionEn = "Describing symptoms, seeing doctors, pharmacy medications, and well-being",
            sentences = listOf(
                SentenceItem("s8_1", "我有点儿头疼，而且一直在发烧。", "Wǒ yǒudiǎnr tóuténg, érqiě yìzhí zài fāshāo.", "Tôi hơi đau đầu, hơn nữa lại đang sốt liên tục.", "I have a slight headache and have been having a fever."),
                SentenceItem("s8_2", "请问挂号处在哪里？", "Qǐngwèn guàhàochù zài nǎlǐ?", "Xin hỏi quầy đăng ký khám bệnh ở đâu ạ?", "Excuse me, where is the registration desk?"),
                SentenceItem("s8_3", "医生，我肚子疼得厉害，昨晚还拉肚子了。", "Yīshēng, wǒ dùzi téng de lìhai, zuówǎn hái lādùzi le.", "Bác sĩ ơi, bụng tôi đau dữ dội, tối qua còn bị tiêu chảy.", "Doctor, I have a severe stomachache and had diarrhea last night."),
                SentenceItem("s8_4", "这个药一天吃三次，每次饭后吃两片。", "Zhè gè yào yì tiān chī sān cì, měi cì fànhòu chī liǎng piàn.", "Thuốc này mỗi ngày uống 3 lần, mỗi lần uống 2 viên sau bữa ăn.", "Take this medicine three times a day, two tablets after meals each time."),
                SentenceItem("s8_5", "你对海鲜或者青霉素过敏吗？", "Nǐ duì hǎixiān huòzhě qīngméisù guòmǐn ma?", "Bạn có bị dị ứng với hải sản hay penicillin không?", "Are you allergic to seafood or penicillin?"),
                SentenceItem("s8_6", "请把手伸出来，我帮你量一下血压。", "Qǐng bǎ shǒu shēn chūlái, wǒ bāng nǐ liáng yíxià xuèyā.", "Xin đưa tay ra, tôi đo huyết áp giúp bạn.", "Please stretch out your arm so I can measure your blood pressure."),
                SentenceItem("s8_7", "多喝温水，注意保暖，多卧床休息。", "Duō hē wēnshuǐ, zhùyì bǎonuǎn, duō wòchuáng xiūxi.", "Uống nhiều nước ấm, chú ý giữ ấm và nghỉ ngơi nhiều trên giường.", "Drink plenty of warm water, keep warm, and get adequate bed rest."),
                SentenceItem("s8_8", "这几天尽量吃清淡一点儿，别吃油腻辛辣的食物。", "Zhè jǐ tiān jǐnliàng chī qīngdàn yìdiǎnr, bié chī yóunì xīnlà de shíwù.", "Mấy ngày này cố gắng ăn thanh đạm một chút, đừng ăn đồ dầu mỡ cay nóng.", "Try to eat light food these days, avoid greasy and spicy foods."),
                SentenceItem("s8_9", "做完检查后，请在大厅等候叫号。", "Zuò wán jiǎnchá hòu, qǐng zài dàtīng děnghòu jiàohào.", "Làm xét nghiệm xong, xin mời đợi gọi số ở đại sảnh.", "After the examination, please wait for your number to be called in the hall."),
                SentenceItem("s8_10", "祝你早日康复，身体健康！", "Zhù nǐ zǎorì kāngfù, shēntǐ jiànkāng!", "Chúc bạn sớm bình phục, sức khỏe dồi dào!", "Wishing you a speedy recovery and good health!")
            )
        ),
        SentenceCategory(
            id = "c9",
            title = "Gia đình & Đời sống",
            titleEn = "Family & Daily Living",
            iconEmoji = "🏡",
            description = "Tình cảm gia đình, nhà cửa, nấu nướng, sinh hoạt thường nhật",
            descriptionEn = "Family relationships, home living, cooking, and daily routines",
            sentences = listOf(
                SentenceItem("s9_1", "周末我喜欢在家打扫房间和做家务。", "Zhōumò wǒ xǐhuan zài jiā dǎsǎo fángjiān hé zuò jiāwù.", "Cuối tuần tôi thích ở nhà quét dọn phòng và làm việc nhà.", "On weekends I like cleaning rooms and doing housework at home."),
                SentenceItem("s9_2", "今天妈妈给我们做了一桌丰盛的晚餐。", "Jīntiān māma gěi wǒmen zuò le yì zhuō fēngshèng de wǎncān.", "Hôm nay mẹ đã nấu cho chúng tôi một bàn bữa tối thịnh soạn.", "Mom cooked a lavish dinner for us today."),
                SentenceItem("s9_3", "爸爸平时最喜欢在院子里养花草。", "Bàba píngshí zuì xǐhuan zài yuànzi lǐ yǎng huācǎo.", "Bố tôi ngày thường thích nhất là trồng hoa cỏ trong sân.", "Dad usually enjoys cultivating flowers and plants in the yard."),
                SentenceItem("s9_4", "一家人平平安安聚在一起就是最大的幸福。", "Yì jiā rén píngpíng-ān'ān jù zài yìqǐ jiù shì zuì dà de xìngfú.", "Cả nhà bình an đoàn tụ bên nhau chính là niềm hạnh phúc lớn nhất.", "A family staying safe and united together is the greatest happiness."),
                SentenceItem("s9_5", "妹妹从小就很懂事，经常帮妈妈洗碗。", "Mèimei cóngxiǎo jiù hěn dǒngshì, chīngcháng bāng māma xǐwǎn.", "Em gái từ nhỏ đã rất hiểu chuyện, thường xuyên giúp mẹ rửa chén bát.", "Younger sister has been very sensible since childhood, often helping mom wash dishes."),
                SentenceItem("s9_6", "晚饭后我们全家人常去公园散步。", "Wǎnfàn hòu wǒmen quánjiā rén cháng qù gōngyuán sànbù.", "Sau bữa cơm tối cả nhà chúng tôi thường đi dạo ở công viên.", "After dinner our whole family often goes for a walk in the park."),
                SentenceItem("s9_7", "我给奶奶买了一副老花镜作为生日礼物。", "Wǒ gěi nǎinai mǎi le yí fù lǎohuājìng zuòwéi shēngrì lǐwù.", "Tôi đã mua cho bà nội một cặp kính lão làm quà sinh nhật.", "I bought a pair of reading glasses for grandma as a birthday gift."),
                SentenceItem("s9_8", "邻里之间互帮互助，相处得十分融洽。", "Línlǐ zhījiān hùbāng hùzhù, xiāngchǔ de shífēn róngqià.", "Hàng xóm láng giềng tương trợ lẫn nhau, chung sống vô cùng hòa thuận.", "Neighbors help each other and get along very harmoniously."),
                SentenceItem("s9_9", "春节是我们全家最隆重团圆的传统节日。", "Chūnjié shì wǒmen quánjiā zuì lóngzhòng tuányuán de chuántǒng jiérì.", "Tết Nguyên đán là ngày hội truyền thống sum họp long trọng nhất của gia đình chúng tôi.", "Spring Festival is our family's most grand traditional reunion holiday."),
                SentenceItem("s9_10", "家永远是我们最温暖避风的港湾。", "Jiā yǒngyuǎn shì wǒmen zuì wēnnuǎn bìfēng de gǎngwān.", "Gia đình mãi mãi là bến đỗ bình yên ấm áp nhất của chúng ta.", "Home is forever our warmest safe haven.")
            )
        ),
        SentenceCategory(
            id = "c10",
            title = "Thời tiết & Cảm xúc",
            titleEn = "Weather & Emotions",
            iconEmoji = "☀️",
            description = "Bốn mùa, khí hậu, biểu đạt cảm xúc vui buồn, hy vọng",
            descriptionEn = "Four seasons, climate, expressing joy, sadness, and hopeful feelings",
            sentences = listOf(
                SentenceItem("s10_1", "今天阳光明媚，微风吹着让人心情格外舒畅。", "Jīntiān yángguāng míngmèi, wēifēng chuī zhe ràng rén xīnqíng géwài shūchàng.", "Hôm nay ánh nắng rực rỡ, gió nhẹ thổi làm tâm trạng khoan khoái vô cùng.", "Today the sun is shining brightly, and gentle breezes make one feel exceptionally refreshed."),
                SentenceItem("s10_2", "天气预报说今天下午可能会有雷阵雨。", "Tiānqì yùbào shuō jīntiān xiàwǔ kěnéng huì yǒu léizhènyǔ.", "Dự báo thời tiết nói chiều nay có khả năng sẽ có mưa dông.", "The weather forecast says there might be thunderstorms this afternoon."),
                SentenceItem("s10_3", "这里的秋天枫叶变红，景色宜人。", "Zhèlǐ de qiūtiān fēngyè biàn hóng, jǐngsè yírén.", "Mùa thu nơi đây lá phong chuyển đỏ, phong cảnh đẹp đẽ lòng người.", "In autumn here, maple leaves turn red and the scenery is pleasant."),
                SentenceItem("s10_4", "听到这个令人振奋的好消息，大家都欢欣鼓舞。", "Tīng dào zhè gè lìngrén zhènfèn de hǎo xiāoxi, dàjiā dōu huānxīn gǔwǔ.", "Nghe được tin vui đáng phấn khởi này, mọi người đều hân hoan vui mừng.", "Hearing this uplifting good news, everyone was elated and excited."),
                SentenceItem("s10_5", "冬天下起了皑皑白雪，到处银装素裹。", "Dōngtiān xià qǐ le ái'ái báixuě, dàochù yínzhuāng sùguǒ.", "Mùa đông tuyết trắng xóa rơi, khắp nơi phủ một màu trắng bạc tinh khôi.", "White snow fell in winter, wrapping everywhere in a silvery-white blanket."),
                SentenceItem("s10_6", "遇到困难不要灰心，一切都会好起来的。", "Yù dào kùnnán bú yào huīxīn, yíqiè dōu huì hǎo qǐlái de.", "Gặp khó khăn đừng nản lòng, mọi chuyện rồi sẽ tốt đẹp lên thôi.", "Do not lose heart when facing difficulties, everything will turn out fine."),
                SentenceItem("s10_7", "夏天天气闷热，大家要多补充水分预防中暑。", "Xiàtiān tiānqì mēnrè, dàjiā yào duō bǔchōng shuǐfèn yùfáng zhòngshǔ.", "Mùa hè thời tiết oi bức, mọi người nhớ uống nhiều nước đề phòng say nắng.", "Summer weather is sultry; please hydrate well to prevent heatstroke."),
                SentenceItem("s10_8", "漫步在雨后的湖边，呼吸着清新湿润的空气。", "Mànbù zài yǔ hòu de hú biān, hūxī zhe qīngxīn shīrùn de kōngqì.", "Tản bộ bên hồ sau cơn mưa, hít thở bầu không khí trong lành ẩm mát.", "Strolling by the lake after rain, breathing in the fresh and moist air."),
                SentenceItem("s10_9", "看到朋友取得成功，我打心底里为他感到骄傲。", "Kàn dào péngyou qǔdé chénggōng, wǒ dǎ xīndǐ lǐ wèi tā gǎndào jiāo'ào.", "Nhìn thấy bạn bè gặt hái thành công, từ tận đáy lòng tôi thấy tự hào về bạn.", "Seeing my friend succeed, I feel truly proud of him from the bottom of my heart."),
                SentenceItem("s10_10", "心怀阳光，无论走到哪里都能遇见美好。", "Xīnhuái yángguāng, wúlùn zǒu dào nǎlǐ dōu néng yùjiàn měihǎo.", "Trong lòng chan chứa ánh nắng, dù đi tới bất cứ đâu cũng sẽ gặp được điều tốt đẹp.", "Keep sunshine in your heart, and you will encounter beauty wherever you go.")
            )
        )
    )
}
