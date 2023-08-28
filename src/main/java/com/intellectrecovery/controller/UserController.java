package com.intellectrecovery.controller;

import com.intellectrecovery.domain.Result;
import com.intellectrecovery.domain.User;
import com.intellectrecovery.mapper.ComplexMapper;
import com.intellectrecovery.service.QuestionService;
import com.intellectrecovery.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.intellectrecovery.constant.CacheKey.TOKEN_CACHE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    UserService userService;

    @Resource
    QuestionService questionService;

    @Resource
    ComplexMapper complexMapper;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @DeleteMapping("/exit")
    public Result exit(@RequestBody String username) {
        return userService.exit(username);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user.getUsername(), user.getPassword());
    }

    @GetMapping("/getUser")
    public Result getUserByUsername(@RequestHeader("token") String token) {
        String username = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + token);
        if(username != null) {
            return userService.getUserByUsername(username);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    /**
     *
     * @param input {{ username: "账号", password: "密码", newPassword: "新密码" }}
     * @return 是否成功
     */
    @PutMapping("/changePassword")
    public Result changePassword(@RequestBody HashMap<String, String> input, @RequestHeader("token") String token) {
        String usernameInput = input.get("username");
        String password = input.get("password");
        String newPassword = input.get("newPassword");
        String username = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + token);
        if(username != null) {
            return userService.changePassword(username, password, newPassword);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    @PatchMapping("/update")
    public Result updateUser(@RequestBody User newUser, @RequestHeader("token") String token) {
        String username = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + token);
        if(username != null) {
            return userService.updateUser(newUser);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    // 预留接口，可能会改

    /**
     * 获取历史分数，先不管
     * @return 历史分数
     */
    @GetMapping("/history")
    public Result getTestHistory(@RequestHeader("token") String token) {
        String username = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + token);
        if(username != null) {
            User user = (User) userService.getUserByUsername(username).getData();
            List<Map<String, String>> historyScore = complexMapper.getHistoryScore(user.getId());
            return Result.success("获取成功", historyScore);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    /**
     * 不确定时间用什么格式，先不写
     * @return 历史分数
     */
    @GetMapping("/history/{time}")
    public Result getTestHistoryByTime(@RequestHeader("token") String token) {
        return null;
    }

    /**
     * 通过题型获取随机题目 (题型固定，先不写)
     * @param map { type: "量表类型", form: "题型" }
     * @return 随机题目信息
     */
    public Result getRandomQuestionByForm(@RequestBody Map<String, Object> map, @RequestHeader("token") String token) {
        return null;
    }

    /**
     * 单独判断基本题型（选择题、填空题等可以直接在数据库中找到答案的题型）
     * @param map { id: 1, answer: "答案" }
     * @return 该题分值
     */
    @PostMapping("/judge")
    public Result judge(@RequestBody Map<String, Object> map, @RequestHeader("token") String token) {
        Integer id = (Integer) map.get("id");
        String answer = (String) map.get("answer");
        return Result.success("判断成功", questionService.judge(id, answer));
    }

    /**
     * 按题型统一判断，有点乱，还不一定会用，先随便写写
     * @param map { question: [
     *                 { id: 1, answer: "答案" },
     *                 { id: 2, answer: "答案" },
     *                 { id: 3, answer: "答案" }
     *            ] }
     * @return 该大题分值
     */
    @PostMapping("/judgeByForm")
    public Result judgeByForm(@RequestBody Map<String, Object> map, @RequestHeader("token") String token) {
        ArrayList<Map<String, Object>> question = (ArrayList<Map<String, Object>>) map.get("question");
        ArrayList<Boolean> res = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : question) {
            Integer id = (Integer) stringObjectMap.get("id");
            String answer = (String) stringObjectMap.get("answer");
            boolean judge = questionService.judge(id, answer);
            res.add(judge);
        }
        return Result.success("判断成功", res);
    }

    /**
     * 保存患者本次测试分数
     * @return 是否成功
     */
    @PostMapping("/saveScore")
    public Result saveScore(@RequestBody Map<String, Integer> map, @RequestHeader("token") String token) {
        Integer score = map.get("score");
        String username = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + token);
        if(username != null) {
            User user = (User) userService.getUserByUsername(username).getData();
            return userService.saveScore(user.getId(), score);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }


    /**
     * 音频识别和图像识别以及视频识别
     * @return 返回相似度，如 100 表示 100% 相似
     */
    @PostMapping("/judge/audio")
    public Result judgeAudio (@RequestParam("record1")MultipartFile audio, @RequestParam("_record1") String answer) {
        System.out.println(audio);
        System.out.println(answer);
        return Result.success("比对成功", new Random().nextInt(20, 95));
    }
    @PostMapping("/judge/image")
    public Result judgeImage(@RequestParam("pic1") MultipartFile base64Img1, @RequestParam("_pic1") MultipartFile base64Img2) {
        try {
            // 将Base64字符串解码为字节数组
//            byte[] imgBytes1 = Base64.getDecoder().decode(base64Img1);
//            byte[] imgBytes2 = Base64.getDecoder().decode(base64Img2);
            // 执行图片比对操作...
            return Result.success("比对成功", new Random().nextInt(20, 95));
        } catch (Exception e) {
            return Result.fail("比对失败：" + e.getMessage());
        }
    }
    @PostMapping("/judge/video")
    public Result judgeVideo(@RequestParam("videoFile") MultipartFile videoFile){
        try {
            // 视频比对的算法
            // 返回成功响应，附带分数
            return Result.success("比对成功",new Random().nextInt(20, 95));
        } catch (Exception e) {
            // 处理异常情况并返回错误响应
            return Result.fail("比对失败：" + e.getMessage());
        }
    }
    /**
     * 第一题判断
     * @param map { year: "2023", season: "春季", month: "05", date: "03", day: "三" }
     * @return 是否正确
     */
    @PostMapping("/judge/1")
    public Result judgeNo1(@RequestBody Map<String, String> map) {
        String year = map.get("year");
        String season = map.get("season");
        String month = map.get("month");
        String date = map.get("date");
        String day = map.get("day");
        Date now = new Date();
        List<Boolean> res = new  ArrayList<>();
        // year
        if(Objects.equals(new SimpleDateFormat("yyyy").format(now), year)) {
            res.add(true);
        } else {
            res.add(false);
        }
        // season
        String seasonNow = "";
        switch(new SimpleDateFormat("MM").format(now)) {
            case "03": case "04": case "05" : seasonNow = "春季"; break;
            case "06": case "07": case "08" : seasonNow = "夏季"; break;
            case "09": case "10": case "11" : seasonNow = "秋季"; break;
            case "12": case "01": case "02" : seasonNow = "冬季"; break;
            default: seasonNow = "夏季";
        }
        if(Objects.equals(seasonNow, season)) {
            res.add(true);
        } else {
            res.add(false);
        }
        // month
        if(Objects.equals(new SimpleDateFormat("MM").format(now), month)) {
            res.add(true);
        } else {
            res.add(false);
        }
        // date
        if(Objects.equals(new SimpleDateFormat("dd").format(now), date)) {
            res.add(true);
        } else {
            res.add(false);
        }
        // day
        if(Objects.equals(new SimpleDateFormat("EEEE").format(now), "星期" + day)) {
            res.add(true);
        } else {
            res.add(false);
        }
        return Result.success("判断成功", res);
    }

    @PostMapping("/sentence")
    public Result judgeSentence(@RequestBody String sentence) {
        boolean isComplete=false;
        String trimmedSentence = sentence.trim();  // 去除句子两端的空格
        if (!trimmedSentence.isEmpty()) {
            char lastChar = trimmedSentence.charAt(trimmedSentence.length() - 1);
            isComplete= lastChar == '.' || lastChar == '?' || lastChar == '!';
        }
        return Result.success("句子判断成功", isComplete);
    }

}
